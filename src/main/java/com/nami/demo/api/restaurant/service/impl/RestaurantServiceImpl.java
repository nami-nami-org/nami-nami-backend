package com.nami.demo.restaurant.service.impl;

import com.nami.demo.model.entity.RestaurantEntity;
import com.nami.demo.restaurant.dto.request.CreateRestaurantRequestDto;
import com.nami.demo.restaurant.dto.response.RestaurantResponseDto;
import com.nami.demo.restaurant.mapper.RestaurantMapper;
import com.nami.demo.restaurant.repository.RestaurantRepository;
import com.nami.demo.restaurant.service.RestaurantService;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public RestaurantResponseDto create(CreateRestaurantRequestDto restaurantRequestDto) {
        RestaurantEntity restaurantEntity = restaurantMapper.toEntity(restaurantRequestDto);
        restaurantEntity = restaurantRepository.save(restaurantEntity);
        return restaurantMapper.toDto(restaurantEntity);
    }
}
