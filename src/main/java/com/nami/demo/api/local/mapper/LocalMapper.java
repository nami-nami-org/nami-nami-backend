package com.nami.demo.api.local.mapper;

import com.nami.demo.api.local.dto.request.CreateLocalRequestDto;
import com.nami.demo.api.local.dto.request.UpdateLocalRequestDto;
import com.nami.demo.api.local.dto.response.LocalResponseDto;
import com.nami.demo.api.restaurant.dto.response.RestaurantResponseDto;
import com.nami.demo.api.restaurant.mapper.RestaurantMapper;
import com.nami.demo.model.entity.LocalEntity;
import com.nami.demo.model.enums.RestaurantStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class LocalMapper {

    private final RestaurantMapper restaurantMapper;

    public LocalMapper(RestaurantMapper restaurantMapper) {
        this.restaurantMapper = restaurantMapper;
    }

    public LocalEntity toEntity(CreateLocalRequestDto dto) {
        LocalEntity entity = new LocalEntity();
        entity.setLocalName(dto.localName());
        entity.setTradeName(dto.tradeName());
        entity.setDescription(dto.description());
        entity.setAddress(dto.address());
        entity.setPhone(dto.phone());
        entity.setDeliveryCost(dto.deliveryCost());
        entity.setDirection(dto.direction());
        entity.setStatus(RestaurantStatus.CERRADO);
        entity.setDeliveryAvailable(true);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public void updateEntity(LocalEntity entity, UpdateLocalRequestDto dto) {
        if (dto.localName() != null) entity.setLocalName(dto.localName());
        if (dto.tradeName() != null) entity.setTradeName(dto.tradeName());
        if (dto.description() != null) entity.setDescription(dto.description());
        if (dto.address() != null) entity.setAddress(dto.address());
        if (dto.phone() != null) entity.setPhone(dto.phone());
        if (dto.deliveryCost() != null) entity.setDeliveryCost(dto.deliveryCost());
        if (dto.direction() != null) entity.setDirection(dto.direction());
        if (dto.deliveryAvailable() != null) entity.setDeliveryAvailable(dto.deliveryAvailable());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    public LocalResponseDto toResponseDto(LocalEntity entity) {

        RestaurantResponseDto restaurantResponse = entity.getRestaurant() != null
                ? restaurantMapper.toDto(entity.getRestaurant())
                : null;

        return new LocalResponseDto(
                entity.getId(),
                entity.getLocalName(),
                entity.getAverageDeliveryTime() != null ? entity.getAverageDeliveryTime() : 0,
                entity.getAverageRating() != null ? entity.getAverageRating().intValue() : 0,
                entity.isDeliveryAvailable(),
                BigDecimal.valueOf(entity.getDeliveryCost()),
                entity.getClosingTime(),
                entity.getCreatedAt(),
                entity.getOpeningTime(),
                restaurantResponse,
                entity.getUpdatedAt(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getTradeName(),
                entity.getDescription(),
                entity.getDirection()
        );
    }
}
