package com.nami.demo.api.localReview.dto.request;

public record CreateLocalReviewRequestDto(
        long localId,
        String comment
) {}