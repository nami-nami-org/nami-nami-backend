package com.nami.demo.api.dishCategory.controller;

import com.nami.demo.api.dishCategory.dto.request.DishCategoryRequestDto;
import com.nami.demo.api.dishCategory.dto.response.DishCategoryResponseDto;
import com.nami.demo.api.dishCategory.service.DishCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish-categories")
public class DishCategoryController {

    private final DishCategoryService dishCategoryService;

    public DishCategoryController(DishCategoryService dishCategoryService) {
        this.dishCategoryService = dishCategoryService;
    }

    @PostMapping
    public ResponseEntity<DishCategoryResponseDto> createCategory(@RequestBody DishCategoryRequestDto dto) {
        return ResponseEntity.ok(dishCategoryService.createCategory(dto));
    }

    @GetMapping
    public ResponseEntity<List<DishCategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(dishCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishCategoryResponseDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(dishCategoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishCategoryResponseDto> updateCategory(
            @PathVariable Long id,
            @RequestBody DishCategoryRequestDto dto
    ) {
        return ResponseEntity.ok(dishCategoryService.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        dishCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
