package com.nami.demo.api.localRating.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateLocalRatingRequestDto(
        Long localId,
        @NotNull
        int rating
) { }