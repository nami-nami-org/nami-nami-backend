package com.nami.demo.api.restaurantReview.service.impl;

import com.nami.demo.api.restaurant.repository.RestaurantRepository;
import com.nami.demo.api.restaurantReview.dto.request.RestaurantReviewRequestDTO;
import com.nami.demo.api.restaurantReview.dto.response.RestaurantReviewResponseDTO;
import com.nami.demo.api.restaurantReview.mapper.RestaurantReviewMapper;
import com.nami.demo.api.restaurantReview.repository.RestaurantReviewRepository;
import com.nami.demo.api.restaurantReview.service.RestaurantReviewService;
import com.nami.demo.api.user.repository.UserRepository;
import com.nami.demo.model.entity.RestaurantEntity;
import com.nami.demo.model.entity.RestaurantReviewEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantReviewServiceImpl implements RestaurantReviewService {

    private final RestaurantReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantReviewMapper mapper;

    public RestaurantReviewServiceImpl(RestaurantReviewRepository reviewRepository,
                                       UserRepository userRepository,
                                       RestaurantRepository restaurantRepository,
                                       RestaurantReviewMapper mapper) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    @Override
    public RestaurantReviewResponseDTO createReview(RestaurantReviewRequestDTO requestDTO) {
        UserEntity user = userRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        RestaurantEntity restaurant = restaurantRepository.findById(requestDTO.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        RestaurantReviewEntity entity = mapper.toEntity(requestDTO, user, restaurant);
        return mapper.toResponse(reviewRepository.save(entity));
    }

    @Override
    public List<RestaurantReviewResponseDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantReviewResponseDTO getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
