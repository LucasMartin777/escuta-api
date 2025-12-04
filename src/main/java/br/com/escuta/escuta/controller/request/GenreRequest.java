package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record GenreRequest(

        @NotBlank
        String name

){}
