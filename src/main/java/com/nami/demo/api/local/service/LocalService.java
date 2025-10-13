package com.nami.demo.api.local.service;

import com.nami.demo.api.local.dto.request.CreateLocalRequestDto;
import com.nami.demo.api.local.dto.response.LocalResponseDto;

public interface LocalService {
    LocalResponseDto getLocalById(long id);

    LocalResponseDto newLocal(CreateLocalRequestDto createLocalRequestDto);
}
