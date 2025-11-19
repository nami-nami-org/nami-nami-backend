package com.nami.demo.api.restaurantReview.service;

import com.nami.demo.api.restaurantReview.dto.request.RestaurantReviewRequestDTO;
import com.nami.demo.api.restaurantReview.dto.response.RestaurantReviewResponseDTO;

import java.util.List;

public interface RestaurantReviewService {

    RestaurantReviewResponseDTO createReview(RestaurantReviewRequestDTO requestDTO);

    List<RestaurantReviewResponseDTO> getAllReviews();

    RestaurantReviewResponseDTO getReviewById(Long id);

    void deleteReview(Long id);
}
