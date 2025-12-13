package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AlbumDetailsResponse(

        Long id,
        String name,
        String albumCover,
        String description,
        LocalDate releaseDate
){}
