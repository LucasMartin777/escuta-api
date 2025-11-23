package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.UserPerfilRegisterRequest;
import br.com.escuta.escuta.controller.response.UserPerfilRegisterResponse;
import br.com.escuta.escuta.entity.UserPerfilEntity;

public class UserPerfilMapper {

    public static UserPerfilEntity toEntity(UserPerfilRegisterRequest request) {
        return UserPerfilEntity.builder()
                .profilePhoto(request.profile_photo())
                .description(request.description())
                .build();
    }

    public static UserPerfilRegisterResponse toDetaislResponse(UserPerfilEntity userPerfilEntity) {
        return UserPerfilRegisterResponse.builder()
                .id(userPerfilEntity.getId())
                .profilePhoto(userPerfilEntity.getProfilePhoto())
                .description(userPerfilEntity.getDescription())
                .build();

    }
}
