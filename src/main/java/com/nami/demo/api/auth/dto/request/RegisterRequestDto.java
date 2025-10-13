package com.nami.demo.api.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 3, max = 255, message = "El nombre debe tener entre 3 y 255 caracteres")
        String name,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ser un email válido")
        @Size(max = 255, message = "El email no debe superar los 255 caracteres")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, max = 100, message = "La contraseña debe tener al menos 8 caracteres")
        String password,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(
                regexp = "^[0-9]{7,15}$",
                message = "El teléfono debe contener solo números y tener entre 7 y 15 dígitos"
        )
        @Size(min = 5, max = 20, message = "El número de teléfono debe tener al menos 5 caracteres")
        String phone
) {
}
