package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record LoginRegisterRequest(


        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        String name,

        @NotBlank
        Date dateOfBirth

) {
}
