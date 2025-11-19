package com.nami.demo.api.dishCategory.service;

import com.nami.demo.api.dishCategory.dto.request.DishCategoryRequestDto;
import com.nami.demo.api.dishCategory.dto.response.DishCategoryResponseDto;

import java.util.List;

public interface DishCategoryService {
    DishCategoryResponseDto createCategory(DishCategoryRequestDto requestDto);

    List<DishCategoryResponseDto> getAllCategories();

    DishCategoryResponseDto getCategoryById(Long categoryId);

    DishCategoryResponseDto updateCategory(Long categoryId, DishCategoryRequestDto requestDto);

    void deleteCategory(Long categoryId);
}
