package com.nami.demo.api.localReview.mapper;

import com.nami.demo.api.localReview.dto.request.CreateLocalReviewRequestDto;
import com.nami.demo.api.localReview.dto.response.LocalReviewResponseDto;
import com.nami.demo.model.entity.LocalReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class LocalReviewMapper {

    public LocalReviewEntity toEntity(CreateLocalReviewRequestDto dto) {
        LocalReviewEntity entity = new LocalReviewEntity();
        entity.setComment(dto.comment());
        entity.setCreatedAt(java.time.LocalDateTime.now());
        return entity;
    }

    public LocalReviewResponseDto toResponseDto(LocalReviewEntity entity) {
        return new LocalReviewResponseDto(
                entity.getId(),
                entity.getComment(),
                entity.getLocal().getId(),
                entity.getUser().getId(),
                entity.getCreatedAt()
        );
    }
}