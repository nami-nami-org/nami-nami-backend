package com.nami.demo.api.localRating.service;

import com.nami.demo.api.localRating.dto.request.CreateLocalRatingRequestDto;
import com.nami.demo.api.localRating.dto.response.LocalRatingResponseDto;

import java.util.List;

public interface LocalRatingService {


    LocalRatingResponseDto newLocalRating(CreateLocalRatingRequestDto dto);

    LocalRatingResponseDto findById(Long id);

    List<LocalRatingResponseDto> findAllByLocal(Long localId);


    LocalRatingResponseDto updateLocalRating(CreateLocalRatingRequestDto dto);

    void deleteLocalRating(Long id);
}
