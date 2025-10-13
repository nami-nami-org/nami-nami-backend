package com.nami.demo.api.dish.mapper;

import com.nami.demo.api.dish.dto.request.CreateDishRequestDto;
import com.nami.demo.api.dish.dto.response.DishResponseDto;
import com.nami.demo.model.entity.DishEntity;
import com.nami.demo.model.entity.LocalEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DishMapper {

    public DishEntity toEntity(CreateDishRequestDto dto) {
        DishEntity dish = new DishEntity();
        dish.setName(dto.name());
        dish.setDescription(dto.description());
        dish.setDiscount(dto.discount());
        dish.setPrice(dto.price());
        dish.setImageUrl(dto.imageUrl());
        dish.setAvailable(dto.available());
        dish.setImageUrls(dto.imageUrls());
        dish.setPrepTime(dto.prepTime());
        dish.setCreatedAt(LocalDateTime.now());
        return dish;
    }

    public DishResponseDto toResponseDto(DishEntity entity) {
        return new DishResponseDto(
                entity.getName(),
                entity.getDescription(),
                entity.getDiscount(),
                entity.getPrice(),
                entity.getImageUrl(),
                entity.isAvailable(),
                entity.getPrepTime()
        );
    }
}
