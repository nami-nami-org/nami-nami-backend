package com.nami.demo.api.user.service;

import com.nami.demo.api.user.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto findById(Long id);
}
