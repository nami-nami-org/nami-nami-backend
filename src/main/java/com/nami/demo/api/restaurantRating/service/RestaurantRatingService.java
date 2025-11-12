package com.nami.demo.api.restaurantRating.service;


import com.nami.demo.api.restaurantRating.dto.request.RestaurantRatingRequestDTO;
import com.nami.demo.api.restaurantRating.dto.response.RestaurantRatingResponseDTO;

import java.util.List;

public interface RestaurantRatingService {

    RestaurantRatingResponseDTO createRating(RestaurantRatingRequestDTO requestDTO);

    List<RestaurantRatingResponseDTO> getAllRatings();

    RestaurantRatingResponseDTO getRatingById(Long id);

    void deleteRating(Long id);
}
