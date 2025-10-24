package com.nami.demo.api.dishRating.dto.response;

import java.time.LocalDateTime;

public record DishRatingResponseDto(
        Long id,
        Long dishId,
        Long userId,
        String userName,
        int rating,
        LocalDateTime createdAt
) { }