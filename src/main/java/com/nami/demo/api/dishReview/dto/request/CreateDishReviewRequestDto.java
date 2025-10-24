package com.nami.demo.api.dishReview.dto.request;


public record CreateDishReviewRequestDto(
    String comment,
    long idDish
) {
}
