package br.com.escuta.escuta.controller.response;

import lombok.Builder;

@Builder
public record AlbumResponse(

        Long id,

        String name,

        int albumDuration,

        String albumCover,

        String description

) {
}
