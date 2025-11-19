package com.nami.demo.api.restaurantReview.controller;

import com.nami.demo.api.restaurantReview.dto.request.RestaurantReviewRequestDTO;
import com.nami.demo.api.restaurantReview.dto.response.RestaurantReviewResponseDTO;
import com.nami.demo.api.restaurantReview.service.RestaurantReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class RestaurantReviewController {

    private final RestaurantReviewService reviewService;

    public RestaurantReviewController(RestaurantReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<RestaurantReviewResponseDTO> create(@RequestBody RestaurantReviewRequestDTO requestDTO) {
        return ResponseEntity.ok(reviewService.createReview(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantReviewResponseDTO>> getAll() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantReviewResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
