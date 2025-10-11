package com.nami.demo.auth.service.impl;

import com.nami.demo.auth.dto.request.LoginRequestDto;
import com.nami.demo.auth.dto.response.LoginResponseDto;
import com.nami.demo.auth.mapper.AuthMapper;
import com.nami.demo.auth.service.AuthService;
import com.nami.demo.auth.strategies.JwtStrategy;
import com.nami.demo.model.entity.UserEntity;
import com.nami.demo.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtStrategy jwt;

    public AuthServiceImpl(UserRepository userRepository, AuthMapper authMapper, PasswordEncoder passwordEncoder, JwtStrategy jwt) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Correo o contraseña icorrectos"));

        boolean validPassword = passwordEncoder.matches(request.password(), user.getPassword());
        if (!validPassword) throw new RuntimeException("Correo o contraseña icorrectos");

        String token = jwt.generateToken(user);
        return authMapper.toResponseDto("Bearer " + token, user);
    }
}
