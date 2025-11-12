package com.nami.demo.api.restaurantRating.dto.request;

public record RestaurantRatingRequestDTO(
        Long userId,
        Long restaurantId,
        int rating
) {}