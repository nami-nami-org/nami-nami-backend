package com.nami.demo.api.dishRating.service;

import com.nami.demo.api.dishRating.dto.request.CreateDishRatingRequestDto;
import com.nami.demo.api.dishRating.dto.response.DishRatingResponseDto;

import java.util.List;

public interface DishRatingService {

    DishRatingResponseDto createRating(CreateDishRatingRequestDto dto);

    DishRatingResponseDto getRatingById(Long id);

    List<DishRatingResponseDto> getAllRatings();

    List<DishRatingResponseDto> getRatingsByDish(Long dishId);

    DishRatingResponseDto updateRating(Long ratingId, CreateDishRatingRequestDto dto);

    void deleteRating(Long ratingId);
}
