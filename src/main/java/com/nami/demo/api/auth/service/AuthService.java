package com.nami.demo.auth.service;

import com.nami.demo.auth.dto.request.LoginRequestDto;
import com.nami.demo.auth.dto.request.RegisterRequestDto;
import com.nami.demo.auth.dto.response.LoginResponseDto;
import com.nami.demo.routes.user.dto.response.UserResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto request);
    UserResponseDto register(RegisterRequestDto request);
}
