package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.LoginRegisterRequest;
import br.com.escuta.escuta.controller.response.LoginDetaisResponse;
import br.com.escuta.escuta.entity.LoginEntity;

public class LoginMapper {
    public static LoginEntity toEntity(LoginRegisterRequest request) {
        return LoginEntity.builder()
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static LoginDetaisResponse toDetaislResponse(LoginEntity loginEntity) {
        return LoginDetaisResponse.builder()
                .id(loginEntity.getId())
                .email(loginEntity.getEmail())
                .userRegisterResponse(UserMapper.toDetaislResponse(loginEntity.getUser()))
                .build();
    }
}
