package com.nami.demo.api.restaurantRating.repository;

import com.nami.demo.model.entity.RestaurantRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRatingRepository extends JpaRepository<RestaurantRatingEntity, Long> {
}
