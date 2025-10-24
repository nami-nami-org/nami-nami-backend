package com.nami.demo.api.dishRating.controller;

import com.nami.demo.api.dishRating.dto.request.CreateDishRatingRequestDto;
import com.nami.demo.api.dishRating.dto.response.DishRatingResponseDto;
import com.nami.demo.api.dishRating.service.DishRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish-ratings")
public class DishRatingController {

    private final DishRatingService dishRatingService;

    public DishRatingController(DishRatingService dishRatingService) {
        this.dishRatingService = dishRatingService;
    }

    @PostMapping
    public ResponseEntity<DishRatingResponseDto> createRating(
            @RequestBody CreateDishRatingRequestDto dto
    ) {
        return ResponseEntity.ok(dishRatingService.createRating(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishRatingResponseDto> getRating(@PathVariable Long id) {
        return ResponseEntity.ok(dishRatingService.getRatingById(id));
    }

    @GetMapping
    public ResponseEntity<List<DishRatingResponseDto>> getAllRatings() {
        return ResponseEntity.ok(dishRatingService.getAllRatings());
    }

    @GetMapping("/dish/{dishId}")
    public ResponseEntity<List<DishRatingResponseDto>> getRatingsByDish(@PathVariable Long dishId) {
        return ResponseEntity.ok(dishRatingService.getRatingsByDish(dishId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishRatingResponseDto> updateRating(
            @PathVariable Long id,
            @RequestBody CreateDishRatingRequestDto dto
    ) {
        return ResponseEntity.ok(dishRatingService.updateRating(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        dishRatingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}
