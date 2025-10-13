package com.nami.demo.api.restaurant.dto.request;

public record CreateRestaurantRequestDto(
    String commercialName,
    String businessName,
    String descripcion,
    String phone,
    String websiteUrl,
    String imageUrl,
    String logoUrl,
    String ruc
) {
}
