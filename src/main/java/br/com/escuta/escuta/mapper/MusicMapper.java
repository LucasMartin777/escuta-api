package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.response.MusicDetailsResponse;
import br.com.escuta.escuta.controller.response.MusicSumaryResponse;
import br.com.escuta.escuta.entity.MusicEntity;

public class MusicMapper {
    public static MusicEntity toEntity(MusicRequest request) {
        return MusicEntity.builder()
                .name(request.name())
                .audioUrl(request.audioUrl())
                .releaseDate(request.releaseDate())
                .singleCover(request.singleCover())
                .build();
    }
    public static MusicSumaryResponse toSumaryResponse(MusicEntity musicEntity) {
        return MusicSumaryResponse.builder()
                .id(musicEntity.getId())
                .name(musicEntity.getName())
                .releaseDate(musicEntity.getReleaseDate())
                .singleCover(musicEntity.getSingleCover())
                .build();
    }

    public static MusicDetailsResponse toDetailsResponse(MusicEntity musicEntity) {
        return MusicDetailsResponse.builder()
                .id(musicEntity.getId())
                .name(musicEntity.getName())
                .audioUrl(musicEntity.getAudioUrl())
                .releaseDate(musicEntity.getReleaseDate())
                .singleCover(musicEntity.getSingleCover())
                .genre(GenreMapper.toDetailsResponse(musicEntity.getGenres()))
                .album(musicEntity.getAlbum() != null
                        ? AlbumMapper.toDetailsResponse(musicEntity.getAlbum())
                        : null)
                .build();
    }


}
