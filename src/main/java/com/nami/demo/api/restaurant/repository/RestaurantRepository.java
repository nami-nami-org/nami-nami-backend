package com.nami.demo.api.restaurant.repository;

import com.nami.demo.model.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
}
