package com.nami.demo.api.local.dto.request;

public record CreateLocalRequestDto(
        String localName,
        String tradeName,
        String description,
        String address,
        String phone,
        double deliveryCost,
        String direction,
        long restaurantId
) { }