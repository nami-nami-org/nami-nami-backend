package com.nami.demo.user.controller;

import com.nami.demo.entity.UserEntity;
import com.nami.demo.user.dto.CreateUserRequestDto;
import com.nami.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createNewUser (@RequestBody CreateUserRequestDto request) {
        UserEntity user = userService.create(request);
        return ResponseEntity.ok(user);
    }
}
