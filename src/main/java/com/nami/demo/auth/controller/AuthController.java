package com.nami.demo.auth.controller;

import com.nami.demo.entity.UserEntity;
import com.nami.demo.user.dto.CreateUserRequestDto;
import com.nami.demo.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createNewUser (@RequestBody CreateUserRequestDto request) {
        UserEntity user = userService.create(request);
        return ResponseEntity.ok(user);
    }
}
