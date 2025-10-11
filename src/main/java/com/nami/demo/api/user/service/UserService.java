package com.nami.demo.routes.user.service;

import com.nami.demo.routes.user.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto findById(Long id);
}
