package com.nami.demo.api.local.dto.response;

import com.nami.demo.api.restaurant.dto.response.RestaurantResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LocalResponseDto(
        long id,
        String localName,
        int averageDeliveryTime,
        int averageRating,
        boolean deliverAvailable,
        BigDecimal deliveryCost,
        LocalDateTime closingTime,
        LocalDateTime createdAt,
        LocalDateTime openingTime,
        RestaurantResponseDto restaurantResponseDto,
        LocalDateTime updateAt,
        String phone,
        String address,
        String tradeName,
        String description,
        String direction
) {

}
