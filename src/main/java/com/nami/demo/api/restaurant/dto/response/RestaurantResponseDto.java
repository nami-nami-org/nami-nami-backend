package com.nami.demo.api.restaurant.dto.response;

import java.math.BigDecimal;

public record RestaurantResponseDto(
        String commercialName,
        String descripcion,
        String phone,
        BigDecimal average
) {
}
