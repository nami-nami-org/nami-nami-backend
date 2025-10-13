package com.nami.demo.api.dish.repository;

import com.nami.demo.model.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<DishEntity, Long> {
}
