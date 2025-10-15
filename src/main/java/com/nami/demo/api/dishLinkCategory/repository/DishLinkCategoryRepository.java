package com.nami.demo.api.dishLinkCategory.repository;

import com.nami.demo.model.entity.DishCategoryLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishLinkCategoryRepository extends JpaRepository<DishCategoryLinkEntity,Long> {
}
