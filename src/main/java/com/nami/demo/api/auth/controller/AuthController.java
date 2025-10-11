package com.nami.demo.api.auth.controller;

import com.nami.demo.api.auth.dto.request.LoginRequestDto;
import com.nami.demo.api.auth.dto.response.LoginResponseDto;
import com.nami.demo.api.auth.service.AuthService;
import com.nami.demo.api.auth.dto.request.RegisterRequestDto;
import com.nami.demo.api.user.dto.response.UserResponseDto;
import com.nami.demo.api.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto request) {
        try {
            UserResponseDto user = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);

        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.BAD_REQUEST.value()
            ));
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) {
        try {
            LoginResponseDto user = authService.login(request);
            return ResponseEntity.ok(user);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", e.getMessage(),
                    "statusCode", HttpStatus.UNAUTHORIZED.value()
            ));
        }
    }
}
