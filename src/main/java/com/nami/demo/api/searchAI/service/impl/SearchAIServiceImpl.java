package com.nami.demo.api.searchAI.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.nami.demo.api.dish.dto.response.DishResponseDto;
import com.nami.demo.api.dish.service.DishService;
import com.nami.demo.api.searchAI.service.SearchAIService;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class SearchAIServiceImpl implements SearchAIService {

    private final DishService dishService;
    private final Client client;
    private final ObjectMapper objectMapper;

    public SearchAIServiceImpl(DishService dishService, Client client, ObjectMapper objectMapper) {
        this.dishService = dishService;
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<DishResponseDto> searchDishByPrompt(String userPrompt) throws JsonProcessingException {

        long startTotal = System.nanoTime();

        long t1 = System.nanoTime();
        List<DishResponseDto> dishes = dishService.findAllDishes();
        long t2 = System.nanoTime();

        String dishesJson = objectMapper.writeValueAsString(dishes);
        long t3 = System.nanoTime();

        String prompt =
                "Eres un asistente estricto que SOLO devuelve JSON válido.\n\n" +
                        "Aquí tienes una lista de platos en formato JSON:\n" +
                        dishesJson + "\n\n" +
                        "Quiero que filtres esta lista según el siguiente criterio:\n\"" + userPrompt + "\"\n\n" +
                        "Reglas obligatorias:\n" +
                        "1. Devuelve SOLO un array JSON válido.\n" +
                        "2. SIN texto explicativo.\n" +
                        "3. SIN backticks.\n" +
                        "4. SIN markdown como ```json.\n" +
                        "5. SIN envolver la respuesta en comillas.\n" +
                        "6. La respuesta debe ser exactamente un array JSON de objetos DishResponseDto.\n" +
                        "Si no encuentras coincidencias, devuelve un array vacío: [].";

        long t4 = System.nanoTime();
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                prompt,
                null
        );
        long t5 = System.nanoTime();

        String cleaned = cleanGeminiResponse(response.text());
        long t6 = System.nanoTime();

        List<DishResponseDto> result = objectMapper.readValue(
                cleaned,
                new TypeReference<List<DishResponseDto>>() {}
        );
        long t7 = System.nanoTime();

        long total = System.nanoTime() - startTotal;

        System.out.println("\n========== MÉTRICAS DE TIEMPO ==========");
        System.out.println("findAllDishes():        " + (t2 - t1) / 1_000_000.0 + " ms");
        System.out.println("writeValueAsString():   " + (t3 - t2) / 1_000_000.0 + " ms");
        System.out.println("Construcción prompt:    " + (t4 - t3) / 1_000_000.0 + " ms");
        System.out.println("Gemini API:             " + (t5 - t4) / 1_000_000.0 + " ms");
        System.out.println("cleanGeminiResponse():  " + (t6 - t5) / 1_000_000.0 + " ms");
        System.out.println("readValue():            " + (t7 - t6) / 1_000_000.0 + " ms");
        System.out.println("----------------------------------------");
        System.out.println("TOTAL:                  " + total / 1_000_000.0 + " ms");
        System.out.println("========================================\n");

        return result;
    }


    private String cleanGeminiResponse(String raw) {
        if (raw == null) return "";

        return raw
                .trim()
                .replaceAll("^```json", "")
                .replaceAll("^```", "")
                .replaceAll("```$", "")
                .replaceAll("```", "")
                .replaceAll("^`+", "")
                .replaceAll("`+$", "")
                .trim();
    }
}
