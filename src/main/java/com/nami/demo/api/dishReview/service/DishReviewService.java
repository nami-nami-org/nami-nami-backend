package com.nami.demo.api.dishReview.service;

import com.nami.demo.api.dishReview.dto.request.CreateDishReviewRequestDto;
import com.nami.demo.api.dishReview.dto.response.DishReviewResponseDto;

import java.util.List;

public interface DishReviewService {
    DishReviewResponseDto createReview(CreateDishReviewRequestDto dto);


    DishReviewResponseDto getReviewById(Long id);

    List<DishReviewResponseDto> getAllReviews();

    DishReviewResponseDto updateReview(CreateDishReviewRequestDto dto, long dishReviewId);

    void deleteReview(Long id);
}
