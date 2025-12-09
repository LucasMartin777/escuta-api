package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;
import java.util.Date;

public record UserRegisterRequest(

        @NotBlank
        String name,

        @NotNull
        @Past
        Date dateOfBirth,

        @NotNull
        LocalDateTime createdAt,

        String profile_photo,

        String description

){}
