package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MusicDetailsResponse(
        Long id,
        String name,
        String audioUrl,
        LocalDate releaseDate,
        String singleCover,
        GenreResponse genre,
        AlbumResponse album
) {}
