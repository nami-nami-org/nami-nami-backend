package com.nami.demo.api.dishCategory.mapper;

import com.nami.demo.api.dishCategory.dto.request.DishCategoryRequestDto;
import com.nami.demo.api.dishCategory.dto.response.DishCategoryResponseDto;
import com.nami.demo.model.entity.DishCategoryEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DishCategoryMapper {

    public DishCategoryEntity toEntity(DishCategoryRequestDto dto) {
        DishCategoryEntity entity = new DishCategoryEntity();
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setType(dto.type());
        entity.setActive(true);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public DishCategoryResponseDto toResponseDto(DishCategoryEntity entity) {
        return new DishCategoryResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getType(),
                entity.getCreatedAt(),
                entity.isActive()
        );
    }
}
