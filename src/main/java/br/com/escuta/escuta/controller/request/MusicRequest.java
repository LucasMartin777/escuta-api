package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MusicRequest(

        @NotBlank
        String name,

        @NotBlank
        String audioUrl,

        @NotNull
        Long genreId,

        LocalDate releaseDate,

        String singleCover,

        Long albumId

){}