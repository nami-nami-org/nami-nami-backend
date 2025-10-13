package com.nami.demo.api.restaurant.mapper;

import com.nami.demo.model.entity.RestaurantEntity;
import com.nami.demo.model.entity.UserEntity;
import com.nami.demo.model.enums.RestaurantStatus;
import com.nami.demo.api.restaurant.dto.request.CreateRestaurantRequestDto;
import com.nami.demo.api.restaurant.dto.response.RestaurantResponseDto;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class RestaurantMapper {

    public RestaurantEntity toEntity(CreateRestaurantRequestDto dto) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setDescription(dto.descripcion());
        restaurantEntity.setPhone(dto.phone());
        restaurantEntity.setUser((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        restaurantEntity.setCommercialName(dto.commercialName());
        restaurantEntity.setAverageRating(null);
        restaurantEntity.setBusinessName(dto.businessName());
        restaurantEntity.setRuc(dto.ruc());
        restaurantEntity.setLogoUrl(dto.logoUrl());
        restaurantEntity.setWebsiteUrl(dto.websiteUrl());
        restaurantEntity.setCreatedAt(LocalDateTime.now());
        restaurantEntity.setUpdatedAt(LocalDateTime.now());
        restaurantEntity.setStatus(RestaurantStatus.ABIERTO);
        restaurantEntity.setLocals(new HashSet<>());
        restaurantEntity.setImageUrl(dto.imageUrl());


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
