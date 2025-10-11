package com.nami.demo.restaurant.dto.request;

public record CreateRestaurantRequestDto(
    String commercialName,
    String descripcion,
    String phone
) {
}
