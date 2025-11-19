package com.nami.demo.api.dishRating.dto.request;

public record CreateDishRatingRequestDto(
        Long dishId,
        int rating
) { }