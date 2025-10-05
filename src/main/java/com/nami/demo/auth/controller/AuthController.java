package com.nami.demo.auth.controller;

import com.nami.demo.model.entity.UserEntity;
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

    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Registra un nuevo usuario en el sistema",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario creado exitosamente",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))
                    )
            }
    )

    @PostMapping
    public ResponseEntity<UserResponseDto> createNewUser(@RequestBody CreateUserRequestDto request) {
        UserResponseDto user = userService.create(request);
        return ResponseEntity.ok(user);
    }
}
