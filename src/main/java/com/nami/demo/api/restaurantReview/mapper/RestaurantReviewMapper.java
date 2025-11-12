package com.nami.demo.api.restaurantReview.mapper;


import com.nami.demo.api.restaurantReview.dto.request.RestaurantReviewRequestDTO;
import com.nami.demo.api.restaurantReview.dto.response.RestaurantReviewResponseDTO;
import com.nami.demo.model.entity.RestaurantReviewEntity;
import com.nami.demo.model.entity.RestaurantEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RestaurantReviewMapper {

    public RestaurantReviewEntity toEntity(RestaurantReviewRequestDTO dto, UserEntity user, RestaurantEntity restaurant) {
        RestaurantReviewEntity entity = new RestaurantReviewEntity();
        entity.setUser(user);
        entity.setRestaurant(restaurant);
        entity.setComment(dto.comment());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public RestaurantReviewResponseDTO toResponse(RestaurantReviewEntity entity) {
        return new RestaurantReviewResponseDTO(
                entity.getId(),
                entity.getUser().getId(),
                entity.getUser().getUsername(), // asegúrate de tener este campo en UserEntity
                entity.getRestaurant().getId(),
                entity.getRestaurant().getBusinessName(), // asegúrate de tener este campo en RestaurantEntity
                entity.getComment(),
                entity.getCreatedAt()
        );
    }
}
