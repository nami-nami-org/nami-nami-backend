package com.nami.demo.auth.service;

import com.nami.demo.auth.dto.request.LoginRequestDto;
import com.nami.demo.auth.dto.response.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto request);
}
