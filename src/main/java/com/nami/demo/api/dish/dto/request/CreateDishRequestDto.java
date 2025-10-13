package com.nami.demo.api.dish.dto.request;

import java.util.Set;

public record CreateDishRequestDto(
        String name,
        String description,
        double discount,
        double price,
        String imageUrl,
        boolean available,
        Set<String> imageUrls,
        Integer prepTime,
        Long localId
) { }