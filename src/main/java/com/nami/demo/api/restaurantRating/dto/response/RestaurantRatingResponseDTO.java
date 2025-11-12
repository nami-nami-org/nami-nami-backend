package com.nami.demo.api.restaurantRating.dto.response;

import java.time.LocalDateTime;

public record RestaurantRatingResponseDTO(
        Long id,
        Long userId,
        String username,
        Long restaurantId,
        String restaurantName,
        int rating,
        LocalDateTime createdAt
) {}