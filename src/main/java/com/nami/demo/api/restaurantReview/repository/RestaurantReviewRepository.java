package com.nami.demo.api.restaurantReview.repository;

import com.nami.demo.model.entity.RestaurantReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantReviewRepository extends JpaRepository<RestaurantReviewEntity, Long> {
}
