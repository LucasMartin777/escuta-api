package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.UserLoginRequest;
import br.com.escuta.escuta.entity.UserLoginEntity;

public class UserLoginMapper {
    public static UserLoginEntity toEntity(UserLoginRequest request) {
        return UserLoginEntity.builder()
                .userName(request.userName())
                .email(request.email())
                .password(request.password())
                .cpf(request.cpf())
                .dateOfBirth(request.dateOfBirth())
                .createdAt(request.createdAt())
                .build();
    }
}
