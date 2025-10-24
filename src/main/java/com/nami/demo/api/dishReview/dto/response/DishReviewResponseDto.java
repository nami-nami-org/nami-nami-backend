package com.nami.demo.api.dishReview.dto.response;

import java.time.LocalDateTime;

public record DishReviewResponseDto(
        Long id,
        String comment,
        Long dishId,
        Long userId,
        String userName,
        LocalDateTime createdAt
) {
}
