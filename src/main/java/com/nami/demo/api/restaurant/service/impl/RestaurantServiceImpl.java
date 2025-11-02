package com.nami.demo.api.restaurant.service.impl;

import com.nami.demo.api.restaurant.dto.request.CreateRestaurantRequestDto;
import com.nami.demo.api.restaurant.dto.response.RestaurantResponseDto;
import com.nami.demo.api.restaurant.mapper.RestaurantMapper;
import com.nami.demo.api.restaurant.repository.RestaurantRepository;
import com.nami.demo.api.restaurant.service.RestaurantService;
import com.nami.demo.integration.cloudinary.FileUploaderService;
import com.nami.demo.integration.cloudinary.dto.response.FileUploaderResponseDto;
import com.nami.demo.model.entity.RestaurantEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final FileUploaderService fileUploaderService;

    public RestaurantServiceImpl(
            RestaurantRepository restaurantRepository,
            RestaurantMapper restaurantMapper,
            FileUploaderService fileUploaderService
    ) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.fileUploaderService = fileUploaderService;
    }

    @Override
    public RestaurantResponseDto create(CreateRestaurantRequestDto dto) {
        try {
            // Mapear datos básicos
            RestaurantEntity restaurantEntity = restaurantMapper.toEntity(dto);

            // 📂 Definir carpeta base para este restaurante
            String folderPath = String.format("restaurants/%s", dto.businessName().replaceAll("\\s+", "_"));

            // 🌆 Subir imagen principal si existe
            if (dto.image() != null && !dto.image().isEmpty()) {
                FileUploaderResponseDto imageUpload = uploadFileSafe(dto.image(), folderPath + "/images");
                restaurantEntity.setImageUrl(imageUpload.secureUrl());
            }

            // 🪪 Subir logo si existe
            if (dto.logo() != null && !dto.logo().isEmpty()) {
                FileUploaderResponseDto logoUpload = uploadFileSafe(dto.logo(), folderPath + "/logos");
                restaurantEntity.setLogoUrl(logoUpload.secureUrl());
            }

            // 💾 Guardar restaurante
            restaurantEntity = restaurantRepository.save(restaurantEntity);
            return restaurantMapper.toDto(restaurantEntity);

        } catch (RuntimeException e) {
            throw new RuntimeException("Error al crear el restaurante: " + e.getMessage(), e);
        }
    }

    @Override
    public RestaurantResponseDto findById(long id) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        return restaurantMapper.toDto(restaurantEntity);
    }

    private FileUploaderResponseDto uploadFileSafe(MultipartFile file, String folderPath) {
        try {
            return fileUploaderService.uploadFile(file, folderPath);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al subir archivo a Cloudinary: " + e.getMessage(), e);
        }
    }
}
