package com.nami.demo.api.dish.dto.response;

public record DishResponseDto(
        String name,
        String description,
        double discount,
        double price,
        String imageUrl,
        boolean available,
        Integer prepTime
) { }