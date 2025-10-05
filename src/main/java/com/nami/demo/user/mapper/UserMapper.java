package com.nami.demo.user.mapper;

import com.nami.demo.model.entity.RolEntity;
import com.nami.demo.model.entity.UserEntity;
import com.nami.demo.user.dto.request.CreateUserRequestDto;
import com.nami.demo.user.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class UserMapper {

    public UserEntity toEntity(CreateUserRequestDto createUserRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(createUserRequestDto.email());
        userEntity.setPassword(createUserRequestDto.password());
        userEntity.setName(createUserRequestDto.name());
        userEntity.setPhone(createUserRequestDto.phone());
        userEntity.setActive(true);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setRoles(new HashSet<>());
        return userEntity;
    }

    public UserResponseDto toDto(UserEntity userEntity) {
        return new UserResponseDto(
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }
}
