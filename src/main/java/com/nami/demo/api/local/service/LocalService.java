package com.nami.demo.api.local.service;

import com.nami.demo.api.local.dto.request.CreateLocalRequestDto;
import com.nami.demo.api.local.dto.request.UpdateLocalRequestDto;
import com.nami.demo.api.local.dto.response.LocalResponseDto;

import java.util.List;

public interface LocalService {
    LocalResponseDto getLocalById(long id);

    LocalResponseDto newLocal(CreateLocalRequestDto createLocalRequestDto);

    List<LocalResponseDto> getAllLocales();

    LocalResponseDto updateLocal(long id, UpdateLocalRequestDto dto);

    void deleteLocal(long id);
}
