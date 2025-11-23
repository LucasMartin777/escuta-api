package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Date;

public record UserLoginRequest(

        @NotBlank
        String userName,

        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotNull
        @Past
        Date dateOfBirth,

        @NotNull
        @PastOrPresent
        LocalDateTime createdAt


) {
}
