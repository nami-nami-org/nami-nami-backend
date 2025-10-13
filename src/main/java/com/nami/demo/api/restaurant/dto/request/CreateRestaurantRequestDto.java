package com.nami.demo.api.restaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record CreateRestaurantRequestDto(
    @NotBlank(message = "El nombre comercial es obligatorio.")
    @Size(max = 200, message = "El nombre comercial no debe superar los 200 caracteres.")
    String commercialName,

    @NotBlank(message = "La razón social es obligatoria.")
    @Size(max = 255, message = "La razón social no debe superar los 255 caracteres.")
    String businessName,

    @NotBlank(message = "La descripción de la empresa es obligatoria.")
    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres.")
    String description,

    @NotBlank(message = "El número de teléfono es obligatorio.")
    @Pattern(
            regexp = "^[0-9+\\-()\\s]{6,20}$",
            message = "El número de teléfono no es válido."
    )
    String phone,

    @Size(max = 255, message = "La URL del sitio web no debe superar los 255 caracteres.")
    @Pattern(
            regexp = "^(https?://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/.*)?$",
            message = "La URL del sitio web no tiene un formato válido."
    )
    String websiteUrl,

    // MultipartFile image,

    // MultipartFile logo,

    @NotBlank(message = "El RUC es obligatorio.")
    @Pattern(regexp = "^[0-9]{11}$", message = "El RUC debe contener exactamente 11 dígitos.")
    String ruc
) {}
