package com.nami.demo.api.dish.dto.response;

import com.nami.demo.api.dishCategory.dto.response.DishCategoryResponseDto;

import java.util.List;
import java.util.Set;

public record DishResponseDto(
        long id,
        String name,
        String description,
        double discount,
        double price,
        String imageUrl,
        boolean available,
        Integer prepTime,
        List<DishCategoryResponseDto> categories) { }