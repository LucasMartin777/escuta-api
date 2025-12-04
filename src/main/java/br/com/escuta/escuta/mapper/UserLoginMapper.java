package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.UserLoginRegisterRequest;
import br.com.escuta.escuta.controller.response.UserLoginDetaisResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;

public class UserLoginMapper {
    public static UserLoginEntity toEntity(UserLoginRegisterRequest request) {
        return UserLoginEntity.builder()
                .userName(request.userName())
                .email(request.email())
                .password(request.password())
                .cpf(request.cpf())
                .dateOfBirth(request.dateOfBirth())
                .createdAt(request.createdAt())
                .build();
    }

    public static UserLoginDetaisResponse toDetaislResponse(UserLoginEntity userLoginEntity) {
        return UserLoginDetaisResponse.builder()
                .id(userLoginEntity.getId())
                .userName(userLoginEntity.getUsername())
                .email(userLoginEntity.getEmail())
                .cpf(userLoginEntity.getCpf())
                .dateOfBirth(userLoginEntity.getDateOfBirth())
                .createdAt(userLoginEntity.getCreatedAt())
                .perfil(UserPerfilMapper.toDetaislResponse(userLoginEntity.getPerfil()))
                .build();
    }
}
