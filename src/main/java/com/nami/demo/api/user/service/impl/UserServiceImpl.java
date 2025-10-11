package com.nami.demo.routes.user.service.impl;

import com.nami.demo.model.entity.UserEntity;
import com.nami.demo.routes.user.dto.response.UserResponseDto;
import com.nami.demo.routes.user.mapper.UserMapper;
import com.nami.demo.routes.user.repository.UserRepository;
import com.nami.demo.routes.user.service.UserService;
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
    public UserResponseDto findById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return userMapper.toDto(user);
    }
}
