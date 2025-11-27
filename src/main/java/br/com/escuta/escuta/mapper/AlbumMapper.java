package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.AlbumRequest;
import br.com.escuta.escuta.controller.response.AlbumResponse;
import br.com.escuta.escuta.entity.AlbumEntity;

public class AlbumMapper {
    public static AlbumEntity toEntity(AlbumRequest request) {
        return AlbumEntity.builder()
                .name(request.name())
                .albumDuration(request.albumDuration())
                .albumCover(request.albumCover())
                .description(request.description())
                .build();
    }

    public static AlbumResponse toDetailsResponse(AlbumEntity entity) {
        return AlbumResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .albumDuration(entity.getAlbumDuration())
                .albumCover(entity.getAlbumCover())
                .description(entity.getDescription())
                .build();
    }
}

