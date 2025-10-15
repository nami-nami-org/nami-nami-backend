package com.nami.demo.api.local.mapper;

import com.nami.demo.api.local.dto.request.CreateLocalRequestDto;
import com.nami.demo.api.local.dto.response.LocalResponseDto;
import com.nami.demo.model.entity.LocalEntity;
import com.nami.demo.model.enums.RestaurantStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalMapper {

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
        entity.setAverageRating(null);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        return entity;
    }


    public LocalResponseDto toResponseDto(LocalEntity entity) {
        return new LocalResponseDto(
                entity.getLocalName(),
                entity.getTradeName(),
                entity.getDescription(),
                entity.getDirection()
        );
    }
}
