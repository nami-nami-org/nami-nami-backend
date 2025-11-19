package com.nami.demo.api.dishRating.mapper;

import com.nami.demo.api.dishRating.dto.request.CreateDishRatingRequestDto;
import com.nami.demo.api.dishRating.dto.response.DishRatingResponseDto;
import com.nami.demo.model.entity.DishEntity;
import com.nami.demo.model.entity.DishRatingEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DishRatingMapper {

    public DishRatingResponseDto toDto(DishRatingEntity entity) {

        return new DishRatingResponseDto(
                entity.getId(),
                entity.getDish().getId(),
                entity.getUser().getId(),
                entity.getUser().getName(),
                entity.getRating(),
                entity.getCreatedAt()
        );
    }

    public DishRatingEntity toEntity(CreateDishRatingRequestDto dto) {

        DishRatingEntity entity = new DishRatingEntity();
        entity.setRating(dto.rating());
        entity.setCreatedAt(LocalDateTime.now());

        return entity;
    }
}
