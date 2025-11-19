package com.nami.demo.api.dishReview.repository;

import com.nami.demo.model.entity.DishReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishReviewRepository extends JpaRepository<DishReviewEntity,Long> {
}
