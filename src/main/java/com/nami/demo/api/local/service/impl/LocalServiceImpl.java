package com.nami.demo.api.local.service.impl;

import com.nami.demo.api.local.dto.request.CreateLocalRequestDto;
import com.nami.demo.api.local.dto.response.LocalResponseDto;
import com.nami.demo.api.local.mapper.LocalMapper;
import com.nami.demo.api.local.repository.LocalRepository;
import com.nami.demo.api.local.service.LocalService;
import com.nami.demo.api.restaurant.repository.RestaurantRepository;
import com.nami.demo.model.entity.LocalEntity;
import com.nami.demo.model.entity.RestaurantEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LocalServiceImpl implements LocalService {

    private final LocalRepository localRepository;
    private final LocalMapper localMapper;
    private final RestaurantRepository restaurantRepository;

    public LocalServiceImpl(LocalRepository localRepository, LocalMapper localMapper, RestaurantRepository restaurantRepository) {
        this.localRepository = localRepository;
        this.localMapper = localMapper;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public LocalResponseDto getLocalById(long id) {
        LocalEntity localEntity = localRepository.findById(id).orElseThrow(()->new RuntimeException("Local no encontrado"));
        return localMapper.toResponseDto(localEntity);
    }

    @Override
    public LocalResponseDto newLocal(CreateLocalRequestDto createLocalRequestDto) {
        LocalEntity localEntity = localMapper.toEntity(createLocalRequestDto);

        RestaurantEntity restaurant = restaurantRepository.findById(createLocalRequestDto.restaurantId()).orElseThrow(()->new RuntimeException("Restaurante no encontrado"));

        //Una pequeña capa de seguridad para que no se creen locales a un restaurante que no le pertenece al usuario logeado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        if (!restaurant.getUser().getEmail().equals(email)) {
            throw new RuntimeException("No puedes crear locales en un restaurante que no te pertenece");
        }

        localEntity.setRestaurant(restaurant);

        localEntity = localRepository.save(localEntity);

        return localMapper.toResponseDto(localEntity);
    }

}
