package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AlbumSumaryResponse(

        Long id,
        String name,
        String albumCover,
        LocalDate releaseDate
){}
