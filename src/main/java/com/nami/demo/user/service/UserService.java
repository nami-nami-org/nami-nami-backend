package com.nami.demo.user.service;

import com.nami.demo.model.entity.UserEntity;
import com.nami.demo.user.dto.request.CreateUserRequestDto;
import com.nami.demo.user.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto create(CreateUserRequestDto request);
}
