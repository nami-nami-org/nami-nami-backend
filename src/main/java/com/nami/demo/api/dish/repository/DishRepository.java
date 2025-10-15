package com.nami.demo.api.dish.repository;

import com.nami.demo.model.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishRepository extends JpaRepository<DishEntity, Long> {

    @Query("SELECT dcl.dish FROM DishCategoryLinkEntity dcl WHERE dcl.category.id = :categoryId")
    List<DishEntity> findDishesByCategoryId(@Param("categoryId") Long categoryId);

}
