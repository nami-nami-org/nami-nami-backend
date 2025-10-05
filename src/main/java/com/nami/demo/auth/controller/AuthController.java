package com.nami.demo.auth.controller;

import com.nami.demo.user.dto.request.CreateUserRequestDto;
import com.nami.demo.user.dto.response.UserResponseDto;
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
    public ResponseEntity<UserResponseDto> createNewUser(@RequestBody CreateUserRequestDto request) {
        UserResponseDto user = userService.create(request);
        return ResponseEntity.ok(user);
    }
}
