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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

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
            response.addCookie(createAuthCookie(user.getToken()));
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.UNAUTHORIZED.value()
            ));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String oldToken = extractTokenFromCookies(request);
            if (oldToken == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "message", "No se encontró token en la cookie",
                        "statusCode", HttpStatus.UNAUTHORIZED.value()
                ));

            String newToken = authService.refreshToken(oldToken);
            response.addCookie(createAuthCookie(newToken));

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

    private Cookie createAuthCookie(String token) {
        boolean isProd = "production".equalsIgnoreCase(ENVIROMENT);
        System.out.println(ENVIROMENT);

        Cookie cookie = new Cookie("Nami_Auth_Session", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(1000 * 60 * 60 * 24); //1  Día
        cookie.setSecure(isProd);
        cookie.setDomain(isProd ? "nami-nami.vercel.app" : "localhost");
        cookie.setAttribute("SameSite", isProd ? "None" : "Lax");
        return cookie;
    }

    private String extractTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies)
            if ("Nami_Auth_Session".equals(cookie.getName()))
                return cookie.getValue();
        return null;
    }
}
