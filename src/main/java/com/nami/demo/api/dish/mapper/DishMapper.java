package com.nami.demo.api.dish.mapper;

import com.nami.demo.api.dish.dto.request.CreateDishRequestDto;
import com.nami.demo.api.dish.dto.response.DishResponseDto;
import com.nami.demo.api.dishCategory.mapper.DishCategoryMapper;
import com.nami.demo.model.entity.DishEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class DishMapper {

    private final DishCategoryMapper dishCategoryMapper;

    public DishMapper(DishCategoryMapper dishCategoryMapper) {
        this.dishCategoryMapper = dishCategoryMapper;
    }

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
        dish.setCategories(new HashSet<>());
        return dish;
    }

    public DishResponseDto toResponseDto(DishEntity entity) {
        return new DishResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getDiscount(),
                entity.getPrice(),
                entity.getImageUrl(),
                entity.isAvailable(),
                entity.getPrepTime(),
                entity.getCreatedAt(),
                entity.getLocal() != null ? entity.getLocal().getId() : null,
                entity.getCategories()
                        .stream()
                        .map(link -> dishCategoryMapper.toResponseDto(link.getCategory()))
                        .toList()
        );
    }
}
