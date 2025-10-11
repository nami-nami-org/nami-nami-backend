package com.nami.demo.auth.dto.response;

import com.nami.demo.routes.user.dto.response.UserResponseDto;

public class LoginResponseDto {
    private String token;
    private UserResponseDto user;

    public LoginResponseDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
}
