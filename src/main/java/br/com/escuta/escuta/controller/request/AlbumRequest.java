package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AlbumRequest(

        @NotBlank
        String name,

        @NotEmpty
        List<Long> music,

        String albumCover,

        String description

) {
}
