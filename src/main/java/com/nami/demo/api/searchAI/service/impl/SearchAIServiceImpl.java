package com.nami.demo.api.searchAI.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.nami.demo.api.dish.dto.response.DishResponseDto;
import com.nami.demo.api.dish.service.DishService;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class SearchAIServiceImpl {

    private final DishService dishService;
    private final Client client;
    private final ObjectMapper objectMapper;

    public SearchAIServiceImpl(DishService dishService, Client client, ObjectMapper objectMapper) {
        this.dishService = dishService;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public List<DishResponseDto> searchDishByPrompt(String userPrompt) throws JsonProcessingException {

        // 1️⃣ Obtener todos los platillos
        List<DishResponseDto> dishes = dishService.findAllDishes();

        // 2️⃣ Convertirlos a JSON
        String dishesJson = objectMapper.writeValueAsString(dishes);

        // 3️⃣ Construir prompt para el AI
        String prompt = "Aquí hay una lista de platillos en JSON:\n"
                + dishesJson
                + "\nFiltra según esta instrucción: " + userPrompt
                + "\nDevuelve la respuesta únicamente en formato JSON.";

        // 4️⃣ Llamar al modelo AI
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                prompt,
                null
        );

        System.out.println(response.text());

        // 5️⃣ Convertir la respuesta JSON a List<DishResponseDto>
        List<DishResponseDto> dishResponseDtos = objectMapper.readValue(
                response.text(),
                new TypeReference<List<DishResponseDto>>() {}
        );

        // 6️⃣ Retornar la lista filtrada
        return dishResponseDtos;
    }
}
