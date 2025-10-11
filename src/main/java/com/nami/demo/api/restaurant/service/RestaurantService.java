package com.nami.demo.restaurant.service;

import com.nami.demo.restaurant.dto.request.CreateRestaurantRequestDto;
import com.nami.demo.restaurant.dto.response.RestaurantResponseDto;

public interface RestaurantService {
    RestaurantResponseDto create(CreateRestaurantRequestDto restaurantRequestDto);
}
