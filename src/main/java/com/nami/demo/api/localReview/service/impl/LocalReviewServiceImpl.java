package com.nami.demo.api.localReview.service.impl;

import com.nami.demo.api.local.repository.LocalRepository;
import com.nami.demo.api.localReview.dto.request.CreateLocalReviewRequestDto;
import com.nami.demo.api.localReview.dto.response.LocalReviewResponseDto;
import com.nami.demo.api.localReview.mapper.LocalReviewMapper;
import com.nami.demo.api.localReview.repository.LocalReviewRepository;
import com.nami.demo.api.localReview.service.LocalReviewService;
import com.nami.demo.api.user.repository.UserRepository;
import com.nami.demo.model.entity.LocalEntity;
import com.nami.demo.model.entity.LocalReviewEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalReviewServiceImpl implements LocalReviewService {

    private final LocalReviewRepository localReviewRepository;
    private final LocalReviewMapper localReviewMapper;
    private final LocalRepository localRepository;
    private final UserRepository userRepository;

    public LocalReviewServiceImpl(LocalReviewRepository localReviewRepository,
                                  LocalReviewMapper localReviewMapper,
                                  LocalRepository localRepository,
                                  UserRepository userRepository) {
        this.localReviewRepository = localReviewRepository;
        this.localReviewMapper = localReviewMapper;
        this.localRepository = localRepository;
        this.userRepository = userRepository;
    }

    @Override
    public LocalReviewResponseDto newLocalReview(CreateLocalReviewRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        LocalEntity local = localRepository.findById(dto.localId())
                .orElseThrow(() -> new RuntimeException("Local no encontrado"));

        LocalReviewEntity review = localReviewMapper.toEntity(dto);
        review.setUser(user);
        review.setLocal(local);

        localReviewRepository.save(review);

        return localReviewMapper.toResponseDto(review);
    }

    @Override
    public LocalReviewResponseDto findById(Long id) {
        LocalReviewEntity entity = localReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
        return localReviewMapper.toResponseDto(entity);
    }

    @Override
    public List<LocalReviewResponseDto> findAllByLocal(Long localId) {
        List<LocalReviewEntity> reviews = localReviewRepository.findByLocalId(localId);
        return reviews.stream()
                .map(localReviewMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public LocalReviewResponseDto updateLocalReview(CreateLocalReviewRequestDto dto, long idLocalReview) {
        LocalReviewEntity review = localReviewRepository.findById(idLocalReview)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        if (!review.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("No tienes permiso para editar esta reseña");
        }

        review.setComment(dto.comment());
        localReviewRepository.save(review);

        return localReviewMapper.toResponseDto(review);
    }

    @Override
    public void deleteLocalReview(Long id) {
        LocalReviewEntity review = localReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        if (!review.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("No tienes permiso para eliminar esta reseña");
        }

        localReviewRepository.delete(review);
    }
}
