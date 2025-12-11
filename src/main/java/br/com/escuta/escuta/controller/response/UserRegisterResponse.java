package br.com.escuta.escuta.controller.response;

import lombok.Builder;

@Builder
public record UserRegisterResponse(

        Long id,
        String name,
        String profilePhoto,
        String description
) {
}
