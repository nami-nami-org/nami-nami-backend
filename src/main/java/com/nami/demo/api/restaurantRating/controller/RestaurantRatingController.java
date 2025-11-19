package com.nami.demo.api.restaurantRating.controller;

import com.nami.demo.api.restaurantRating.dto.request.RestaurantRatingRequestDTO;
import com.nami.demo.api.restaurantRating.dto.response.RestaurantRatingResponseDTO;
import com.nami.demo.api.restaurantRating.service.RestaurantRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RestaurantRatingController {

    private final RestaurantRatingService ratingService;

    public RestaurantRatingController(RestaurantRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<RestaurantRatingResponseDTO> create(@RequestBody RestaurantRatingRequestDTO requestDTO) {
        return ResponseEntity.ok(ratingService.createRating(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantRatingResponseDTO>> getAll() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantRatingResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ratingService.getRatingById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}
