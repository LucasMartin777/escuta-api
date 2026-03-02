package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.response.MusicDetailsResponse;
import br.com.escuta.escuta.controller.response.MusicSumaryResponse;
import br.com.escuta.escuta.entity.AlbumEntity;
import br.com.escuta.escuta.entity.GenreEntity;
import br.com.escuta.escuta.entity.MusicEntity;
import br.com.escuta.escuta.entity.UserEntity;

public class MusicMapper {
    public static MusicEntity toEntity(MusicRequest request,
                                       UserEntity user,
                                       GenreEntity genre,
                                       AlbumEntity album) {
        return MusicEntity.builder()
                .name(request.name())
                .audioUrl(request.audioUrl())
                .releaseDate(request.releaseDate())
                .singleCover(request.singleCover())
                .user(user)
                .genre(genre)
                .album(album)
                .build();
    }

    public static MusicSumaryResponse toSumaryResponse(MusicEntity musicEntity) {
        return MusicSumaryResponse.builder()
                .id(musicEntity.getId())
                .name(musicEntity.getName())
                .releaseDate(musicEntity.getReleaseDate())
                .singleCover(musicEntity.getSingleCover())
                .albumId(
                        musicEntity.getAlbum() != null
                                ? musicEntity.getAlbum().getId()
                                : null
                )
                .build();
    }

    public static MusicDetailsResponse toDetailsResponse(MusicEntity musicEntity) {
        return MusicDetailsResponse.builder()
                .id(musicEntity.getId())
                .name(musicEntity.getName())
                .audioUrl(musicEntity.getAudioUrl())
                .releaseDate(musicEntity.getReleaseDate())
                .singleCover(musicEntity.getSingleCover())
                .genre(GenreMapper.toDetailsResponse(musicEntity.getGenre()))
                .album(musicEntity.getAlbum() != null
                        ? AlbumMapper.toSumaryResponse(musicEntity.getAlbum())
                        : null)
                .build();
    }
}
