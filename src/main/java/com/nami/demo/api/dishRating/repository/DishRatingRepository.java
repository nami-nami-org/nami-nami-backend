package com.nami.demo.api.dishRating.repository;

import com.nami.demo.model.entity.DishRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRatingRepository extends JpaRepository<DishRatingEntity, Long> {
    List<DishRatingEntity> findByDishId(Long dishId);
}