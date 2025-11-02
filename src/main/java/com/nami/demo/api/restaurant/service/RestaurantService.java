package com.nami.demo.api.restaurant.service;

import com.nami.demo.api.restaurant.dto.request.CreateRestaurantRequestDto;
import com.nami.demo.api.restaurant.dto.response.RestaurantResponseDto;

public interface RestaurantService {
    RestaurantResponseDto create(CreateRestaurantRequestDto restaurantRequestDto);

    RestaurantResponseDto findById(long id);
    
    // RestaurantEntity create(CreateRestaurantRequestDto restaurantRequestDto);
}
