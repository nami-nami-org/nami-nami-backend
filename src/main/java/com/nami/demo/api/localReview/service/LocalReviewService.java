package com.nami.demo.api.localReview.service;

import com.nami.demo.api.localReview.dto.request.CreateLocalReviewRequestDto;
import com.nami.demo.api.localReview.dto.response.LocalReviewResponseDto;

import java.util.List;

public interface LocalReviewService {


    LocalReviewResponseDto newLocalReview(CreateLocalReviewRequestDto dto);

    LocalReviewResponseDto findById(Long id);

    List<LocalReviewResponseDto> findAllByLocal(Long localId);


    LocalReviewResponseDto updateLocalReview(CreateLocalReviewRequestDto dto, long idLocalReview);

    void deleteLocalReview(Long id);
}