package com.nami.demo.api.auth.controller;

import com.nami.demo.api.auth.dto.request.LoginRequestDto;
import com.nami.demo.api.auth.dto.response.LoginResponseDto;
import com.nami.demo.api.auth.dto.request.RegisterRequestDto;
import com.nami.demo.api.auth.service.AuthService;
import com.nami.demo.api.user.dto.response.UserResponseDto;
import com.nami.demo.api.user.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Value("${nami.env}")
    private String ENVIROMENT;

    public AuthController(UserService userService, AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto request) {
        try {
            UserResponseDto user = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.BAD_REQUEST.value()
            ));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request, HttpServletResponse response) {
        try {
            LoginResponseDto user = authService.login(request);

            attachAuthCookie(response, user.getToken());

            return ResponseEntity.ok(user.getUser());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.UNAUTHORIZED.value()
            ));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(
            HttpServletRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ) {
        try {
            String token = extractTokenFromCookies(request);

            if (token == null && authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }

            UserResponseDto user = authService.isTokenValid(token);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "isValid", false,
                        "message", "Token inválido o expirado"
                ));
            }

            return ResponseEntity.ok(user);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "isValid", false,
                    "message", e.getMessage()
            ));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String oldToken = extractTokenFromCookies(request);
            if (oldToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "message", "No se encontró token en la cookie",
                        "statusCode", HttpStatus.UNAUTHORIZED.value()
                ));
            }

            String newToken = authService.refreshToken(oldToken);

            attachAuthCookie(response, newToken);

            return ResponseEntity.ok(Map.of(
                    "message", "Token actualizado exitosamente",
                    "token", newToken
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.UNAUTHORIZED.value()
            ));
        }
    }

    private void attachAuthCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from("Nami_Auth_Session", token)
                .domain("http://localhost:4321")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(60 * 60 * 24)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    private String extractTokenFromCookies(HttpServletRequest request) {
        if (request == null || request.getCookies() == null) return null;

        for (Cookie c : request.getCookies()) {
            if ("Nami_Auth_Session".equals(c.getName())) {
                return c.getValue();
            }
        }
        return null;
    }
}
