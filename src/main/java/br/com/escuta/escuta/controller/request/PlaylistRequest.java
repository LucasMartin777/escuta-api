package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PlaylistRequest(

        @NotBlank
        String name,

        String cover,

        @NotNull
        Boolean isPublic,

        LocalDate registrationDate
) {
}

