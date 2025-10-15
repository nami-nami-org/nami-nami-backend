package com.nami.demo.api.dish.service.impl;

import com.nami.demo.api.dish.dto.request.CreateDishRequestDto;
import com.nami.demo.api.dish.dto.response.DishResponseDto;
import com.nami.demo.api.dish.mapper.DishMapper;
import com.nami.demo.api.dish.repository.DishRepository;
import com.nami.demo.api.dish.service.DishService;
import com.nami.demo.api.dishCategory.repository.DishCategoryRepository;
import com.nami.demo.api.dishLinkCategory.repository.DishLinkCategoryRepository;
import com.nami.demo.api.local.repository.LocalRepository;
import com.nami.demo.api.user.repository.UserRepository;
import com.nami.demo.model.entity.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final UserRepository userRepository;
    private final LocalRepository localRepository;
    private final DishCategoryRepository dishCategoryRepository;
    private final DishLinkCategoryRepository dishLinkCategoryRepository;

    public DishServiceImpl(DishRepository dishRepository, DishMapper dishMapper, UserRepository userRepository, LocalRepository localRepository, DishCategoryRepository dishCategoryRepository, DishLinkCategoryRepository dishLinkCategoryRepository) {
        this.dishRepository = dishRepository;
        this.dishMapper = dishMapper;
        this.userRepository = userRepository;
        this.localRepository = localRepository;
        this.dishCategoryRepository = dishCategoryRepository;
        this.dishLinkCategoryRepository = dishLinkCategoryRepository;
    }

    @Override
    public DishResponseDto findDishById(Long id) {
        DishEntity dishEntity = dishRepository.findById(id).orElseThrow(()->new RuntimeException("Platillo no encontrado"));

        return dishMapper.toResponseDto(dishEntity);
    }

    @Override
    public List<DishResponseDto> findAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(dishMapper::toResponseDto)
                .toList();
    }

    @Override
    public DishResponseDto updateDish(Long id, CreateDishRequestDto updateDto) {
        DishEntity dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Platillo no encontrado"));

        dish.setName(updateDto.name());
        dish.setDescription(updateDto.description());
        dish.setDiscount(updateDto.discount());
        dish.setPrice(updateDto.price());
        dish.setImageUrl(updateDto.imageUrl());
        dish.setAvailable(updateDto.available());
        dish.setImageUrls(updateDto.imageUrls());
        dish.setPrepTime(updateDto.prepTime());

        dish = dishRepository.save(dish);

        return dishMapper.toResponseDto(dish);
    }


    @Override
    public void deleteDish(Long id) {
        DishEntity dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Platillo no encontrado"));
        dishRepository.delete(dish);
    }


    @Override
    public DishResponseDto newDish(CreateDishRequestDto createDishRequestDto) {
        DishEntity dishEntity = dishMapper.toEntity(createDishRequestDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        LocalEntity local = localRepository.findByIdAndUserId(createDishRequestDto.localId(), user.getId())
                .orElseThrow(() -> new RuntimeException("No tienes permiso para este local"));

        dishEntity.setLocal(local);

        dishEntity = dishRepository.save(dishEntity);

        return dishMapper.toResponseDto(dishEntity);
    }

    @Override
    public List<DishResponseDto> findDishesByCategoryId(Long categoryId) {
        return dishRepository.findDishesByCategoryId(categoryId).stream().map(dishMapper::toResponseDto).toList();
    }

    @Override
    public DishResponseDto addCategoryToDish(Long dishId, Long categoryId) {
        DishEntity dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Platillo no encontrado"));

        DishCategoryEntity category = dishCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        DishCategoryLinkEntity link = new DishCategoryLinkEntity();
        link.setDish(dish);
        link.setCategory(category);
        link.setCreatedAt(LocalDateTime.now());

        dishLinkCategoryRepository.save(link);

        if (dish.getCategories() == null) {
            dish.setCategories(new HashSet<>());
        }
        dish.getCategories().add(link);

        dish = dishRepository.save(dish);

        return dishMapper.toResponseDto(dish);
    }

}
