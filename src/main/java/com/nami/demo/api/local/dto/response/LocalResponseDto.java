package com.nami.demo.api.local.dto.response;

public record LocalResponseDto(
        String localName,
        String tradeName,
        String description,
        String direction
) {

}
