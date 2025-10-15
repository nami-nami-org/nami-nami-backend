package com.nami.demo.api.dish.controller;

import com.nami.demo.api.dish.dto.request.CreateDishRequestDto;
import com.nami.demo.api.dish.dto.response.DishResponseDto;
import com.nami.demo.api.dish.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getDishById(@PathVariable Long id) {
        DishResponseDto dish = dishService.findDishById(id);
        return ResponseEntity.ok(dish);
    }

    @PostMapping
    public ResponseEntity<DishResponseDto> createDish(@RequestBody CreateDishRequestDto createDishRequestDto) {
        DishResponseDto newDish = dishService.newDish(createDishRequestDto);
        return new ResponseEntity<>(newDish, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<DishResponseDto> > findDishesByCategoryId(@PathVariable Long categoryId) {
        List<DishResponseDto> dishList = dishService.findDishesByCategoryId(categoryId);
        return ResponseEntity.ok().body(dishList);
    }

    @PatchMapping("/{dishId}/category/{categoryId}")
    public ResponseEntity<DishResponseDto> addCategoryToDish(@PathVariable Long dishId, @PathVariable Long categoryId) {
        DishResponseDto updatedDish = dishService.addCategoryToDish(dishId, categoryId);
        return ResponseEntity.ok(updatedDish);
    }

    @GetMapping
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        List<DishResponseDto> dishes = dishService.findAllDishes();
        return ResponseEntity.ok(dishes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishResponseDto> updateDish(@PathVariable Long id, @RequestBody CreateDishRequestDto updateDto) {
        DishResponseDto updatedDish = dishService.updateDish(id, updateDto);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

}
