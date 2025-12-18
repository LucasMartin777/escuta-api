package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AlbumUpdateRequest(


        String name,

        List<Long> music,

        String albumCover,

        String description

) {
}
