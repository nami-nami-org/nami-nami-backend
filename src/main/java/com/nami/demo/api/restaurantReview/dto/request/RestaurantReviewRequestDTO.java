package com.nami.demo.api.restaurantReview.dto.request;

public record RestaurantReviewRequestDTO(
        Long userId,
        Long restaurantId,
        String comment
) {}