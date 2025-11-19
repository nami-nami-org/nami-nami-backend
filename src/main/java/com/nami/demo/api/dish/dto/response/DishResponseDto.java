package com.nami.demo.api.dish.dto.response;

import com.nami.demo.api.dishCategory.dto.response.DishCategoryResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record DishResponseDto(
        long id,
        String name,
        String description,
        double discount,
        double price,
        String imageUrl,
        boolean available,
        Integer prepTime,
        LocalDateTime createdAt,
        Long localId,
        List<DishCategoryResponseDto> categories
) {}