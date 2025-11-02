package com.nami.demo.integration.cloudinary.dto.response;

public record FileUploaderResponseDto(
        String publicId,
        String secureUrl,
        String extension
) {}
