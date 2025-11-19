package com.nami.demo.api.dishReview.service.impl;

import com.nami.demo.api.dish.repository.DishRepository;
import com.nami.demo.api.dishReview.dto.request.CreateDishReviewRequestDto;
import com.nami.demo.api.dishReview.dto.response.DishReviewResponseDto;
import com.nami.demo.api.dishReview.mapper.DishReviewMapper;
import com.nami.demo.api.dishReview.repository.DishReviewRepository;
import com.nami.demo.api.dishReview.service.DishReviewService;
import com.nami.demo.api.user.repository.UserRepository;
import com.nami.demo.model.entity.DishEntity;
import com.nami.demo.model.entity.DishReviewEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishReviewServiceImpl implements DishReviewService {

    private final DishReviewRepository dishReviewRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;
    private final DishReviewMapper dishReviewMapper;

    public DishReviewServiceImpl(
            DishReviewRepository dishReviewRepository,
            DishRepository dishRepository,
            UserRepository userRepository,
            DishReviewMapper dishReviewMapper
    ) {
        this.dishReviewRepository = dishReviewRepository;
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
        this.dishReviewMapper = dishReviewMapper;
    }

    @Override
    public DishReviewResponseDto createReview(CreateDishReviewRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        DishEntity dish = dishRepository.findById(dto.idDish())
                .orElseThrow(() -> new RuntimeException("Platillo no encontrado con ID: " + dto.idDish()));

        DishReviewEntity review = dishReviewMapper.toEntity(dto);
        review.setUser(user);
        review.setDish(dish);

        DishReviewEntity saved = dishReviewRepository.save(review);
        return dishReviewMapper.toDto(saved);
    }

    @Override
    public DishReviewResponseDto getReviewById(Long id) {
        DishReviewEntity entity = dishReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada con ID: " + id));
        return dishReviewMapper.toDto(entity);
    }

    @Override
    public List<DishReviewResponseDto> getAllReviews() {
        return dishReviewRepository.findAll()
                .stream()
                .map(dishReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DishReviewResponseDto updateReview(CreateDishReviewRequestDto dto, long dishReviewId) {
        DishReviewEntity entity = dishReviewRepository.findById(dishReviewId)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada con ID: " + dto.idDish()));

        DishReviewEntity updated = dishReviewMapper.toEntity(dto);

        updated.setUser(entity.getUser());
        updated.setId(entity.getId());
        updated.setCreatedAt(entity.getCreatedAt());

        dishReviewRepository.save(updated);

        return dishReviewMapper.toDto(updated);
    }

    @Override
    public void deleteReview(Long id) {
        if (!dishReviewRepository.existsById(id)) {
            throw new RuntimeException("Reseña no encontrada con ID: " + id);
        }
        dishReviewRepository.deleteById(id);
    }
}
