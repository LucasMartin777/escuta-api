package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.AlbumRequest;
import br.com.escuta.escuta.controller.response.AlbumDetailsResponse;
import br.com.escuta.escuta.controller.response.AlbumSumaryResponse;
import br.com.escuta.escuta.entity.AlbumEntity;
import br.com.escuta.escuta.entity.UserEntity;

public class AlbumMapper {
    public static AlbumEntity toEntity(AlbumRequest request, UserEntity user) {
        return AlbumEntity.builder()
                .name(request.name())
                .albumCover(request.albumCover())
                .description(request.description())
                .user(user)
                .build();
    }

    public static AlbumDetailsResponse toDetailsResponse(AlbumEntity entity) {
        return AlbumDetailsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .albumCover(entity.getAlbumCover())
                .releaseDate(entity.getReleaseDate())
                .description(entity.getDescription())
                .build();
    }

    public static AlbumSumaryResponse toSumaryResponse(AlbumEntity entity) {
        return AlbumSumaryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .albumCover(entity.getAlbumCover())
                .releaseDate(entity.getReleaseDate())
                .build();
    }
}

