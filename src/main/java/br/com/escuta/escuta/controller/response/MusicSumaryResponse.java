package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MusicSumaryResponse(

        Long id,
        String name,
        LocalDate releaseDate,
        String singleCover
){}

