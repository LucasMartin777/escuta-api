package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PlaylistRequest(

        @NotBlank
        String name,

        Boolean isPublic,

        String cover,

        List<Long> music

) {
}

