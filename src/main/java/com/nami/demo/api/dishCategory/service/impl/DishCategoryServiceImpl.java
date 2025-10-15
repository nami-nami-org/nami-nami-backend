package com.nami.demo.api.dishCategory.service.impl;

import com.nami.demo.api.dishCategory.dto.request.DishCategoryRequestDto;
import com.nami.demo.api.dishCategory.dto.response.DishCategoryResponseDto;
import com.nami.demo.api.dishCategory.mapper.DishCategoryMapper;
import com.nami.demo.api.dishCategory.repository.DishCategoryRepository;
import com.nami.demo.api.dishCategory.service.DishCategoryService;
import com.nami.demo.model.entity.DishCategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishCategoryServiceImpl implements DishCategoryService {

    private final DishCategoryRepository dishCategoryRepository;
    private final DishCategoryMapper dishCategoryMapper;

    public DishCategoryServiceImpl(DishCategoryRepository dishCategoryRepository, DishCategoryMapper dishCategoryMapper) {
        this.dishCategoryRepository = dishCategoryRepository;
        this.dishCategoryMapper = dishCategoryMapper;
    }

    @Override
    public DishCategoryResponseDto createCategory(DishCategoryRequestDto requestDto) {
        DishCategoryEntity entity = dishCategoryMapper.toEntity(requestDto);
        entity = dishCategoryRepository.save(entity);
        return dishCategoryMapper.toResponseDto(entity);
    }

    @Override
    public List<DishCategoryResponseDto> getAllCategories() {
        List<DishCategoryEntity> categories = dishCategoryRepository.findAll();
        return categories.stream()
                .map(dishCategoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public DishCategoryResponseDto getCategoryById(Long categoryId) {
        DishCategoryEntity dishCategory = dishCategoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return dishCategoryMapper.toResponseDto(dishCategory);
    }
}
