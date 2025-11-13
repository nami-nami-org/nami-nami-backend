package com.nami.demo.api.local.dto.request;

public record UpdateLocalRequestDto(
        String localName,
        String tradeName,
        String description,
        String address,
        String phone,
        Double deliveryCost,
        String direction,
        Boolean deliveryAvailable
) {}