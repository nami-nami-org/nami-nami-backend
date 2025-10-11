package com.nami.demo.user.service.impl;

import com.nami.demo.model.entity.UserEntity;
import com.nami.demo.user.dto.request.CreateUserRequestDto;
import com.nami.demo.user.dto.response.UserResponseDto;
import com.nami.demo.user.mapper.UserMapper;
import com.nami.demo.user.repository.UserRepository;
import com.nami.demo.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto create(CreateUserRequestDto request) {
        if(userRepository.existsByEmail(request.email())) {
            throw new RuntimeException(String.format("El usuario con el correo %s ya existe", request.email()));
        }

        UserEntity user = userMapper.toEntity(request);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}
