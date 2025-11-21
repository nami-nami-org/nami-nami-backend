package com.nami.demo.api.searchAI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nami.demo.api.dish.dto.response.DishResponseDto;

import java.util.List;

public interface SearchAIService {
    List<DishResponseDto> searchDishByPrompt(String userPrompt) throws JsonProcessingException;
}
