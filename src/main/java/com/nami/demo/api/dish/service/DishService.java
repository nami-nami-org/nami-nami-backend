package com.nami.demo.api.dish.service;

import com.nami.demo.api.dish.dto.request.CreateDishRequestDto;
import com.nami.demo.api.dish.dto.response.DishResponseDto;
import jakarta.transaction.Transactional;

public interface DishService {
    DishResponseDto findDishById(Long id);

    DishResponseDto newDish(CreateDishRequestDto createDishRequestDto);
}
