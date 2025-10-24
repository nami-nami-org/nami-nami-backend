package com.nami.demo.api.dishReview.mapper;

import com.nami.demo.api.dishReview.dto.request.CreateDishReviewRequestDto;
import com.nami.demo.api.dishReview.dto.response.DishReviewResponseDto;
import com.nami.demo.model.entity.DishEntity;
import com.nami.demo.model.entity.DishReviewEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DishReviewMapper {

    public DishReviewResponseDto toDto(DishReviewEntity entity) {

        return new DishReviewResponseDto(
                entity.getId(),
                entity.getComment(),
                entity.getDish().getId(),
                entity.getUser().getId(),
                entity.getUser().getName(),
                entity.getCreatedAt()
        );
    }


    public DishReviewEntity toEntity(CreateDishReviewRequestDto dto) {
        DishReviewEntity entity = new DishReviewEntity();
        entity.setComment(dto.comment());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }
}
