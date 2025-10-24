package com.nami.demo.api.dishReview.controller;

import com.nami.demo.api.dishReview.dto.request.CreateDishReviewRequestDto;
import com.nami.demo.api.dishReview.dto.response.DishReviewResponseDto;
import com.nami.demo.api.dishReview.service.DishReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish-reviews")
public class DishReviewController {

    private final DishReviewService dishReviewService;

    public DishReviewController(DishReviewService dishReviewService) {
        this.dishReviewService = dishReviewService;
    }

    @PostMapping
    public ResponseEntity<DishReviewResponseDto> createReview(
            @RequestBody CreateDishReviewRequestDto dto
    ) {
        return ResponseEntity.ok(dishReviewService.createReview(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishReviewResponseDto> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(dishReviewService.getReviewById(id));
    }

    @GetMapping
    public ResponseEntity<List<DishReviewResponseDto>> getAllReviews() {
        return ResponseEntity.ok(dishReviewService.getAllReviews());
    }

    @PutMapping("/{dishReviewId}")
    public ResponseEntity<DishReviewResponseDto> updateReview(
            CreateDishReviewRequestDto dto,
            @PathVariable long dishReviewId
    ) {
        return ResponseEntity.ok(dishReviewService.updateReview(dto,dishReviewId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        dishReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
