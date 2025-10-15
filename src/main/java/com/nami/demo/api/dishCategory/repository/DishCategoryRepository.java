package com.nami.demo.api.dishCategory.repository;

import com.nami.demo.model.entity.DishCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishCategoryRepository extends JpaRepository<DishCategoryEntity, Long> {
}
