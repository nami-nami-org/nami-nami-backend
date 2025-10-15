package com.nami.demo.api.dish.service;

import com.nami.demo.api.dish.dto.request.CreateDishRequestDto;
import com.nami.demo.api.dish.dto.response.DishResponseDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface DishService {
    DishResponseDto findDishById(Long id);

    List<DishResponseDto> findAllDishes();

    DishResponseDto updateDish(Long id, CreateDishRequestDto updateDto);

    void deleteDish(Long id);

    DishResponseDto newDish(CreateDishRequestDto createDishRequestDto);

    List<DishResponseDto> findDishesByCategoryId(Long categoryId);

    DishResponseDto addCategoryToDish(Long dishId, Long categoryId);
}
