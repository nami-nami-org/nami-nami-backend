package com.nami.demo.api.dishCategory.dto.response;

import java.time.LocalDateTime;

public record DishCategoryResponseDto(
        String name,
        String description,
        String type,
        LocalDateTime createdAt,
        boolean active
) {
}
