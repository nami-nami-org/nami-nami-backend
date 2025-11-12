package com.nami.demo.api.restaurantRating.mapper;

import com.nami.demo.api.restaurantRating.dto.request.RestaurantRatingRequestDTO;
import com.nami.demo.api.restaurantRating.dto.response.RestaurantRatingResponseDTO;
import com.nami.demo.model.entity.RestaurantEntity;
import com.nami.demo.model.entity.RestaurantRatingEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RestaurantRatingMapper {

    public RestaurantRatingEntity toEntity(RestaurantRatingRequestDTO dto, UserEntity user, RestaurantEntity restaurant) {
        RestaurantRatingEntity entity = new RestaurantRatingEntity();
        entity.setUser(user);
        entity.setRestaurant(restaurant);
        entity.setRating(dto.rating());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public RestaurantRatingResponseDTO toResponse(RestaurantRatingEntity entity) {
        return new RestaurantRatingResponseDTO(
                entity.getId(),
                entity.getUser().getId(),
                entity.getUser().getUsername(), // asegúrate de tener este campo en UserEntity
                entity.getRestaurant().getId(),
                entity.getRestaurant().getBusinessName(), // asegúrate de tener este campo en RestaurantEntity
                entity.getRating(),
                entity.getCreatedAt()
        );
    }
}
