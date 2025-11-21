package com.nami.demo.api.searchAI.controller;

import com.nami.demo.api.dish.dto.response.DishResponseDto;
import com.nami.demo.api.searchAI.service.SearchAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchAi")
public class SearchAIController {

    private final SearchAIService searchAIService;

    public SearchAIController(SearchAIService searchAIService) {
        this.searchAIService = searchAIService;
    }
    /**
     * Endpoint para filtrar platillos mediante un prompt de AI.
     * Ejemplo: GET /api/ai/search?prompt=vegetarian
     */
    @GetMapping
    public ResponseEntity<List<DishResponseDto>> searchDishes(@RequestParam("prompt") String prompt) {
        try {
            List<DishResponseDto> filteredDishes = searchAIService.searchDishByPrompt(prompt);
            return ResponseEntity.ok(filteredDishes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
