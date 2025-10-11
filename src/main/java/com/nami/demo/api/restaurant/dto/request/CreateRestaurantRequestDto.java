package com.nami.demo.api.restaurant.dto.request;

public record CreateRestaurantRequestDto(
    String commercialName,
    String descripcion,
    String phone
) {
}
