package com.nami.demo.api.restaurantRating.service.impl;


import com.nami.demo.api.restaurant.repository.RestaurantRepository;
import com.nami.demo.api.restaurantRating.dto.request.RestaurantRatingRequestDTO;
import com.nami.demo.api.restaurantRating.dto.response.RestaurantRatingResponseDTO;
import com.nami.demo.api.restaurantRating.mapper.RestaurantRatingMapper;
import com.nami.demo.api.restaurantRating.repository.RestaurantRatingRepository;
import com.nami.demo.api.restaurantRating.service.RestaurantRatingService;
import com.nami.demo.api.user.repository.UserRepository;
import com.nami.demo.model.entity.RestaurantEntity;
import com.nami.demo.model.entity.RestaurantRatingEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantRatingServiceImpl implements RestaurantRatingService {

    private final RestaurantRatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantRatingMapper mapper;

    public RestaurantRatingServiceImpl(RestaurantRatingRepository ratingRepository,
                                       UserRepository userRepository,
                                       RestaurantRepository restaurantRepository,
                                       RestaurantRatingMapper mapper) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    @Override
    public RestaurantRatingResponseDTO createRating(RestaurantRatingRequestDTO requestDTO) {
        UserEntity user = userRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        RestaurantEntity restaurant = restaurantRepository.findById(requestDTO.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        RestaurantRatingEntity entity = mapper.toEntity(requestDTO, user, restaurant);
        return mapper.toResponse(ratingRepository.save(entity));
    }

    @Override
    public List<RestaurantRatingResponseDTO> getAllRatings() {
        return ratingRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantRatingResponseDTO getRatingById(Long id) {
        return ratingRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Valoración no encontrada"));
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}
