package com.nami.demo.api.localRating.dto.request;

public record CreateLocalRatingRequestDto(
        Long localId,
        int rating
) { }