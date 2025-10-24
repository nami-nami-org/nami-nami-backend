package com.nami.demo.api.local.dto.response;

public record LocalResponseDto(
        long id,
        String localName,
        String tradeName,
        String description,
        String direction
) {

}
