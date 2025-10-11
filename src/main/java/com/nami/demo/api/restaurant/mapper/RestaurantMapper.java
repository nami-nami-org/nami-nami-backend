package com.nami.demo.api.restaurant.mapper;

import com.nami.demo.model.entity.RestaurantEntity;
import com.nami.demo.model.enums.RestaurantStatus;
import com.nami.demo.api.restaurant.dto.request.CreateRestaurantRequestDto;
import com.nami.demo.api.restaurant.dto.response.RestaurantResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RestaurantMapper {

    public RestaurantEntity toEntity(CreateRestaurantRequestDto dto) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setDescription(dto.descripcion());
        restaurantEntity.setPhone(dto.phone());
        restaurantEntity.setUser(null);
        restaurantEntity.setCommercialName(dto.commercialName());
        restaurantEntity.setAverageRating(null);
        restaurantEntity.setCreatedAt(LocalDateTime.now());
        restaurantEntity.setUpdatedAt(LocalDateTime.now());
        restaurantEntity.setStatus(RestaurantStatus.ABIERTO);

        return restaurantEntity;
    }

    public RestaurantResponseDto toDto( RestaurantEntity entity) {
        return new RestaurantResponseDto(
                entity.getCommercialName(),
                entity.getDescription(),
                entity.getPhone()
        );
    }

}
