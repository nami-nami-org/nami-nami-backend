package com.nami.demo.api.auth.service.impl;

import com.nami.demo.api.auth.dto.request.LoginRequestDto;
import com.nami.demo.api.auth.dto.response.LoginResponseDto;
import com.nami.demo.api.auth.mapper.AuthMapper;
import com.nami.demo.api.auth.service.AuthService;
import com.nami.demo.api.auth.strategy.JwtStrategy;
import com.nami.demo.model.entity.UserEntity;
import com.nami.demo.api.auth.dto.request.RegisterRequestDto;
import com.nami.demo.api.user.dto.response.UserResponseDto;
import com.nami.demo.api.user.mapper.UserMapper;
import com.nami.demo.api.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtStrategy jwt;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository, AuthMapper authMapper, PasswordEncoder passwordEncoder, JwtStrategy jwt, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto register(RegisterRequestDto request) {
        if(userRepository.existsByEmail(request.email()))
            throw new RuntimeException(String.format("El usuario con el correo %s ya existe", request.email()));

        UserEntity user = userMapper.toEntity(request);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }


    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Correo o contraseña icorrectos"));

        boolean validPassword = passwordEncoder.matches(request.password(), user.getPassword());
        if (!validPassword) throw new RuntimeException("Correo o contraseña icorrectos");

        String token = jwt.generateToken(user);
        return authMapper.toResponseDto(token, user);
    }

    @Override
    public UserResponseDto getUserSession(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return userMapper.toDto(user);
    }


    @Override
    public UserResponseDto isTokenValid(String token) {
        try {
            if (token == null || token.isBlank()) {
                return null;
            }

            String username = jwt.extractUsername(token);

            if (username == null) return null;

            UserEntity user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado en la base de datos"));

            boolean valid = jwt.isTokenValid(token, user);

            if (!valid) return null;

            UserResponseDto dto = new UserResponseDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setActive(user.isActive());
            dto.setCreatedAt(user.getCreatedAt());
            dto.setUpdatedAt(user.getUpdatedAt());
            dto.setRoles(user.getRoles());

            return dto;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String refreshToken(String oldToken) {
        String username = jwt.extractUsername(oldToken);
        if (username == null)
            throw new RuntimeException("Token inválido");

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!jwt.isTokenValid(oldToken, user))
            throw new RuntimeException("Token expirado o inválido");

        return jwt.generateToken(user);
    }

}
