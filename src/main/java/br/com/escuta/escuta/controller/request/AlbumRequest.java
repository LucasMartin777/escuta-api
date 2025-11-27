package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;

public record AlbumRequest(

        @NotBlank
        String name,

        int albumDuration,

        String albumCover,

        String description

) {
}
