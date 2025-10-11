package com.nami.demo.routes.user.controller;
import com.nami.demo.routes.user.dto.response.UserResponseDto;
import com.nami.demo.routes.user.service.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> findById (@PathVariable @Positive(message = "El ID debe ser un numero") Long id) {
        UserResponseDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
}
