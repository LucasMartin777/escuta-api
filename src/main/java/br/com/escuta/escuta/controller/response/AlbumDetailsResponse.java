package br.com.escuta.escuta.controller.response;

import br.com.escuta.escuta.entity.MusicEntity;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record AlbumDetailsResponse(

        Long id,
        String name,
        String albumCover,
        String description,
        LocalDate releaseDate,
        List<MusicSumaryResponse> musics
) {
}
