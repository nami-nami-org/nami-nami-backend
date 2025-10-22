package com.nami.demo.api.localReview.dto.response;

import java.time.LocalDateTime;

public record LocalReviewResponseDto(
        Long id,
        String comment,
        Long localId,
        Long userId,
        String userName,
        LocalDateTime createdAt
) {}