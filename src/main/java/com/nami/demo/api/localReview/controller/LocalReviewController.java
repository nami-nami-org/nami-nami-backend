package com.nami.demo.api.localReview.controller;

import com.nami.demo.api.localReview.dto.request.CreateLocalReviewRequestDto;
import com.nami.demo.api.localReview.dto.response.LocalReviewResponseDto;
import com.nami.demo.api.localReview.service.LocalReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local-reviews")
public class LocalReviewController {

    private final LocalReviewService localReviewService;

    public LocalReviewController(LocalReviewService localReviewService) {
        this.localReviewService = localReviewService;
    }

    @PostMapping("/local")
    public ResponseEntity<LocalReviewResponseDto> createReview(
            @RequestBody CreateLocalReviewRequestDto dto) {

        LocalReviewResponseDto response = localReviewService.newLocalReview(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalReviewResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(localReviewService.findById(id));
    }

    @GetMapping("/local/{localId}")
    public ResponseEntity<List<LocalReviewResponseDto>> getAllByLocal(@PathVariable Long localId) {
        return ResponseEntity.ok(localReviewService.findAllByLocal(localId));
    }

    @PutMapping
    public ResponseEntity<LocalReviewResponseDto> updateReview(
            @RequestBody CreateLocalReviewRequestDto dto) {

        return ResponseEntity.ok(localReviewService.updateLocalReview(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        localReviewService.deleteLocalReview(id);
        return ResponseEntity.noContent().build();
    }
}
