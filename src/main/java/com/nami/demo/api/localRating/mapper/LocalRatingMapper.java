package com.nami.demo.api.localRating.mapper;


import com.nami.demo.api.localRating.dto.request.CreateLocalRatingRequestDto;
import com.nami.demo.api.localRating.dto.response.LocalRatingResponseDto;
import com.nami.demo.model.entity.LocalEntity;
import com.nami.demo.model.entity.LocalRatingEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalRatingMapper {

    public LocalRatingEntity toEntity(CreateLocalRatingRequestDto dto) {
        LocalRatingEntity entity = new LocalRatingEntity();
        entity.setRating(dto.rating());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public LocalRatingResponseDto toResponseDto(LocalRatingEntity entity) {
        return new LocalRatingResponseDto(
                entity.getId(),
                entity.getLocal().getId(),
                entity.getUser().getId(),
                entity.getUser().getName(),
                entity.getRating(),
                entity.getCreatedAt()
        );
    }
}
