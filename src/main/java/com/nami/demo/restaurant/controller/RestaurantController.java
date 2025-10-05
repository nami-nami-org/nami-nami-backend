package com.nami.demo.restaurant.controller;

import com.nami.demo.restaurant.dto.request.CreateRestaurantRequestDto;
import com.nami.demo.restaurant.dto.response.RestaurantResponseDto;
import com.nami.demo.restaurant.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createNewRestaurant(@RequestBody CreateRestaurantRequestDto createRestaurantRequestDto) {
        RestaurantResponseDto restaurantResponseDto = restaurantService.create(createRestaurantRequestDto);
        return ResponseEntity.ok(restaurantResponseDto);
    }
}
