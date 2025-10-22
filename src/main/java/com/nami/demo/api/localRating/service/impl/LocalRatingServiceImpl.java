package com.nami.demo.api.localRating.service.impl;

import com.nami.demo.api.local.repository.LocalRepository;
import com.nami.demo.api.localRating.dto.request.CreateLocalRatingRequestDto;
import com.nami.demo.api.localRating.dto.response.LocalRatingResponseDto;
import com.nami.demo.api.localRating.mapper.LocalRatingMapper;
import com.nami.demo.api.localRating.repository.LocalRatingRepository;
import com.nami.demo.api.localRating.service.LocalRatingService;
import com.nami.demo.api.user.repository.UserRepository;
import com.nami.demo.model.entity.LocalEntity;
import com.nami.demo.model.entity.LocalRatingEntity;
import com.nami.demo.model.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalRatingServiceImpl implements LocalRatingService {

    private final LocalRatingRepository localRatingRepository;
    private final LocalRatingMapper localRatingMapper;
    private final LocalRepository localRepository;
    private final UserRepository userRepository;

    public LocalRatingServiceImpl(LocalRatingRepository localRatingRepository,
                                  LocalRatingMapper localRatingMapper,
                                  LocalRepository localRepository,
                                  UserRepository userRepository) {
        this.localRatingRepository = localRatingRepository;
        this.localRatingMapper = localRatingMapper;
        this.localRepository = localRepository;
        this.userRepository = userRepository;
    }


    @Override
    public LocalRatingResponseDto newLocalRating(CreateLocalRatingRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        LocalEntity local = localRepository.findById(dto.localId())
                .orElseThrow(() -> new RuntimeException("Local con ese ID no encontrado"));

        LocalRatingEntity localRatingEntity = localRatingMapper.toEntity(dto);
        localRatingEntity.setUser(user);
        localRatingEntity.setLocal(local);

        localRatingEntity = localRatingRepository.save(localRatingEntity);

        return localRatingMapper.toResponseDto(localRatingEntity);
    }


    @Override
    public LocalRatingResponseDto findById(Long id) {
        LocalRatingEntity entity = localRatingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Valoración no encontrada"));
        return localRatingMapper.toResponseDto(entity);
    }

    @Override
    public List<LocalRatingResponseDto> findAllByLocal(Long localId) {
        List<LocalRatingEntity> ratings = localRatingRepository.findByLocalId(localId);
        return ratings.stream()
                .map(localRatingMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public LocalRatingResponseDto updateLocalRating(CreateLocalRatingRequestDto dto) {
        LocalRatingEntity rating = localRatingRepository.findById(dto.localId())
                .orElseThrow(() -> new RuntimeException("Valoración no encontrada"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        if (!rating.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("No tienes permiso para editar esta valoración");
        }

        rating.setRating(dto.rating());

        localRatingRepository.save(rating);

        return localRatingMapper.toResponseDto(rating);
    }

    @Override
    public void deleteLocalRating(Long id) {
        LocalRatingEntity rating = localRatingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Valoración no encontrada"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        if (!rating.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("No tienes permiso para eliminar esta valoración");
        }

        localRatingRepository.delete(rating);
    }
}
