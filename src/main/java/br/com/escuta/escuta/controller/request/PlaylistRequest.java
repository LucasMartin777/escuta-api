package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PlaylistRequest(

        @NotBlank
        String name,

        @NotNull
        Boolean isPublic,

        String cover,

        LocalDate registrationDate

){}

