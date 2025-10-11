package com.nami.demo.auth.mapper;

import com.nami.demo.auth.dto.response.LoginResponseDto;
import com.nami.demo.model.entity.UserEntity;
import com.nami.demo.routes.user.dto.response.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    private final ModelMapper mapper;

    public AuthMapper(PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.mapper = mapper;
    }

    public LoginResponseDto toResponseDto(String token, UserEntity user) {
        UserResponseDto userDto = mapper.map(user, UserResponseDto.class);
        LoginResponseDto response = new LoginResponseDto();
        response.setToken(token);
        response.setUser(userDto);
        return response;
    }
}
