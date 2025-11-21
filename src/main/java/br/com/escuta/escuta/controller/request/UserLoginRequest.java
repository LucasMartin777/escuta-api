package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(

        @NotBlank
        String email,

        @NotBlank
        String password

) {
}
