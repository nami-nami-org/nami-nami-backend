package com.nami.demo.user.dto.request;

public record CreateUserRequestDto (
        String name,
        String email,
        String password,
        String phone
){
}
