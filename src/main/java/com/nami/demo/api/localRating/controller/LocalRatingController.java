package com.nami.demo.api.localRating.controller;

import com.nami.demo.api.localRating.dto.request.CreateLocalRatingRequestDto;
import com.nami.demo.api.localRating.dto.response.LocalRatingResponseDto;
import com.nami.demo.api.localRating.service.LocalRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local-ratings")
public class LocalRatingController {

    private final LocalRatingService localRatingService;

    public LocalRatingController(LocalRatingService localRatingService) {
        this.localRatingService = localRatingService;
    }

    @PostMapping
    public ResponseEntity<LocalRatingResponseDto> createLocalRating(
            @RequestBody CreateLocalRatingRequestDto dto) {

        LocalRatingResponseDto response = localRatingService.newLocalRating(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalRatingResponseDto> getById(@PathVariable Long id) {
        LocalRatingResponseDto response = localRatingService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/local/{localId}")
    public ResponseEntity<List<LocalRatingResponseDto>> getAllByLocal(@PathVariable Long localId) {
        List<LocalRatingResponseDto> responses = localRatingService.findAllByLocal(localId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalRatingResponseDto> updateLocalRating(
            @RequestBody CreateLocalRatingRequestDto dto,
            @PathVariable long id
    ) {
        LocalRatingResponseDto response = localRatingService.updateLocalRating(dto,id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalRating(@PathVariable Long id) {
        localRatingService.deleteLocalRating(id);
        return ResponseEntity.noContent().build();
    }
}
