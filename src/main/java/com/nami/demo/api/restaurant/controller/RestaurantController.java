package com.nami.demo.api.restaurant.controller;

import com.nami.demo.api.local.dto.request.CreateLocalRequestDto;
import com.nami.demo.api.restaurant.dto.request.CreateRestaurantRequestDto;
import com.nami.demo.api.restaurant.dto.response.RestaurantResponseDto;
import com.nami.demo.api.restaurant.service.RestaurantService;
import com.nami.demo.model.entity.RestaurantEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNewRestaurant(
            @Valid @ModelAttribute CreateRestaurantRequestDto request
    ) {
        try {
            RestaurantResponseDto restaurantResponseDto = restaurantService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurantResponseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message", e.getMessage(),
                "statusCode", HttpStatus.BAD_REQUEST.value()
            ));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> findRestaurantById(@PathVariable long id) {
        RestaurantResponseDto restaurantResponseDto = restaurantService.findById(id);
        return ResponseEntity.ok(restaurantResponseDto);
    }
}