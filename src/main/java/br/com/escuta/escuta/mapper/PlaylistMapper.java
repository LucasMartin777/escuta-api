package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.PlaylistRequest;
import br.com.escuta.escuta.controller.response.PlaylistResponse;
import br.com.escuta.escuta.entity.PlaylistEntity;

public class PlaylistMapper {
    public static PlaylistEntity toEntity(PlaylistRequest request) {
        return PlaylistEntity.builder()
                .name(request.name())
                .cover(request.cover())
                .isPublic(request.isPublic())
                .registrationDate(request.registrationDate())
                .build();
    }

    public static PlaylistResponse toDetailsResponse(PlaylistEntity entity) {
        return PlaylistResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cover(entity.getCover())
                .isPublic(entity.isPublic())
                .registrationDate(entity.getRegistrationDate())
                .build();
    }
}
