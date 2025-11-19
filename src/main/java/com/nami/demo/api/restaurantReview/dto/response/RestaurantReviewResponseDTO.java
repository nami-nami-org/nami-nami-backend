package com.nami.demo.api.restaurantReview.dto.response;

import java.time.LocalDateTime;

public record RestaurantReviewResponseDTO(
        Long id,
        Long userId,
        String username,
        Long restaurantId,
        String restaurantName,
        String comment,
        LocalDateTime createdAt
) {}