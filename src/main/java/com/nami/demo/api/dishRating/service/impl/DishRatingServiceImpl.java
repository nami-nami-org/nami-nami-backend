package com.nami.demo.api.dishRating.service.impl;

import com.nami.demo.api.dishRating.dto.request.CreateDishRatingRequestDto;
import com.nami.demo.api.dishRating.dto.response.DishRatingResponseDto;
import com.nami.demo.api.dishRating.mapper.DishRatingMapper;
import com.nami.demo.api.dishRating.repository.DishRatingRepository;
import com.nami.demo.api.dishRating.service.DishRatingService;
import com.nami.demo.api.dish.repository.DishRepository;
import com.nami.demo.api.user.repository.UserRepository;
import com.nami.demo.model.entity.DishEntity;
import com.nami.demo.model.entity.DishRatingEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishRatingServiceImpl implements DishRatingService {

    private final DishRatingRepository dishRatingRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;
    private final DishRatingMapper dishRatingMapper;

    public DishRatingServiceImpl(DishRatingRepository dishRatingRepository,
                                 DishRepository dishRepository,
                                 UserRepository userRepository,
                                 DishRatingMapper dishRatingMapper) {
        this.dishRatingRepository = dishRatingRepository;
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
        this.dishRatingMapper = dishRatingMapper;
    }

    @Override
    public DishRatingResponseDto createRating(CreateDishRatingRequestDto dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        DishEntity dish = dishRepository.findById(dto.dishId())
                .orElseThrow(() -> new RuntimeException("Platillo no encontrado"));

        DishRatingEntity entity = dishRatingMapper.toEntity(dto);
        entity.setUser(user);
        entity.setDish(dish);

        DishRatingEntity saved = dishRatingRepository.save(entity);
        return dishRatingMapper.toDto(saved);
    }

    @Override
    public DishRatingResponseDto getRatingById(Long id) {
        DishRatingEntity entity = dishRatingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating no encontrado"));
        return dishRatingMapper.toDto(entity);
    }

    @Override
    public List<DishRatingResponseDto> getAllRatings() {
        return dishRatingRepository.findAll()
                .stream()
                .map(dishRatingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DishRatingResponseDto> getRatingsByDish(Long dishId) {
        return dishRatingRepository.findAll()
                .stream()
                .filter(r -> r.getDish().getId().equals(dishId))
                .map(dishRatingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DishRatingResponseDto updateRating(Long dishRatingId, CreateDishRatingRequestDto dto) {
        DishRatingEntity entity = dishRatingRepository.findById(dishRatingId)
                .orElseThrow(() -> new RuntimeException("Rating no encontrado con ID: " + dishRatingId));

        entity.setRating(dto.rating());

        DishRatingEntity updated = dishRatingRepository.save(entity);

        return dishRatingMapper.toDto(updated);
    }


    @Override
    public void deleteRating(Long ratingId) {
        if (!dishRatingRepository.existsById(ratingId)) {
            throw new RuntimeException("Rating no encontrado");
        }
        dishRatingRepository.deleteById(ratingId);
    }
}
