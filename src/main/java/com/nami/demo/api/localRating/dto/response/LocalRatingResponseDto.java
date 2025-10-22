package com.nami.demo.api.localRating.dto.response;

import java.time.LocalDateTime;

public record LocalRatingResponseDto(
        Long id,
        Long localId,
        Long userId,
        String userName,
        int rating,
        LocalDateTime createdAt
) { }