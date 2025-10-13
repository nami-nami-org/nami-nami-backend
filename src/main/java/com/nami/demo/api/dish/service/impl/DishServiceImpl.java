package com.nami.demo.api.dish.service.impl;

import com.nami.demo.api.dish.dto.request.CreateDishRequestDto;
import com.nami.demo.api.dish.dto.response.DishResponseDto;
import com.nami.demo.api.dish.mapper.DishMapper;
import com.nami.demo.api.dish.repository.DishRepository;
import com.nami.demo.api.dish.service.DishService;
import com.nami.demo.api.local.repository.LocalRepository;
import com.nami.demo.api.user.repository.UserRepository;
import com.nami.demo.model.entity.DishEntity;
import com.nami.demo.model.entity.LocalEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final UserRepository userRepository;
    private final LocalRepository localRepository;

    public DishServiceImpl(DishRepository dishRepository, DishMapper dishMapper, UserRepository userRepository, LocalRepository localRepository) {
        this.dishRepository = dishRepository;
        this.dishMapper = dishMapper;
        this.userRepository = userRepository;
        this.localRepository = localRepository;
    }

    @Override
    public DishResponseDto findDishById(Long id) {
        DishEntity dishEntity = dishRepository.findById(id).orElseThrow(()->new RuntimeException("Platillo no encontrado"));

        return dishMapper.toResponseDto(dishEntity);
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

}
