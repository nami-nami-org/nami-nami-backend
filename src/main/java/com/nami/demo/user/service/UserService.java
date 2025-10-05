package com.nami.demo.user.service;

import com.nami.demo.entity.UserEntity;
import com.nami.demo.user.dto.CreateUserRequestDto;
import com.nami.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity create(CreateUserRequestDto request) {
        if (userRepository.existsByEmail(request.email)) {
            throw new RuntimeException("El correo ya esta registrado");
        }

        UserEntity user = new UserEntity();
        user.setName(request.name);
        user.setPhone(request.phone);
        user.setEmail(request.email);
        user.setEmail(request.password);
        // String encryptedPassword = passwordEncoder.encode(request.password);
       // user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

}
