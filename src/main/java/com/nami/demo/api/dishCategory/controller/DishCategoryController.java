package com.nami.demo.api.dishCategory.controller;

import com.nami.demo.api.dishCategory.dto.request.DishCategoryRequestDto;
import com.nami.demo.api.dishCategory.dto.response.DishCategoryResponseDto;
import com.nami.demo.api.dishCategory.service.DishCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/dish-categories")
public class DishCategoryController {

    private final DishCategoryService dishCategoryService;

    public DishCategoryController(DishCategoryService dishCategoryService) {
        this.dishCategoryService = dishCategoryService;
    }

    @PostMapping
    public ResponseEntity<DishCategoryResponseDto> createCategory(@RequestBody DishCategoryRequestDto requestDto) {
        DishCategoryResponseDto createdCategory = dishCategoryService.createCategory(requestDto);

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DishCategoryResponseDto>> getAllCategories() {
        List<DishCategoryResponseDto> categories = dishCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishCategoryResponseDto> getCategoryById(@PathVariable Long id) {
        DishCategoryResponseDto category = dishCategoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }
}
