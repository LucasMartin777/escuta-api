package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LoginRegisterRequest(


        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        String name,

        @NotNull
        LocalDate dateOfBirth

) {
}
