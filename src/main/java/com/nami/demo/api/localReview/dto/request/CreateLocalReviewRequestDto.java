package com.nami.demo.api.localReview.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateLocalReviewRequestDto(
        long localId,
        @NotNull
        String comment
) {}