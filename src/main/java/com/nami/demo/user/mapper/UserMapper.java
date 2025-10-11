package com.nami.demo.user.mapper;

import com.nami.demo.model.entity.UserEntity;
import com.nami.demo.user.dto.request.CreateUserRequestDto;
import com.nami.demo.user.dto.response.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public UserMapper(PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public UserEntity toEntity(CreateUserRequestDto dto) {
        UserEntity user = new UserEntity();
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setName(dto.name());
        user.setPhone(dto.phone());
        return user;
    }

    public UserResponseDto toDto(UserEntity user) {
        return mapper.map(user, UserResponseDto.class);
    }
}
