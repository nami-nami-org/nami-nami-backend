package com.nami.demo.api.auth.service;

import com.nami.demo.api.auth.dto.request.LoginRequestDto;
import com.nami.demo.api.auth.dto.request.RegisterRequestDto;
import com.nami.demo.api.auth.dto.response.LoginResponseDto;
import com.nami.demo.api.user.dto.response.UserResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto request);
    UserResponseDto register(RegisterRequestDto request);
}
