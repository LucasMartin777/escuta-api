package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MusicRequest(

        @NotBlank
        String name,

        @NotBlank
        String audioUrl,

        LocalDate releaseDate,

        String singleCover,

        @NotNull
        Long genreId,

        Long albumId
) {
}