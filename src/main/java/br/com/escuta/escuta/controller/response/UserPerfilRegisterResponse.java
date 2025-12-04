package br.com.escuta.escuta.controller.response;

import lombok.Builder;

@Builder
public record UserPerfilRegisterResponse(

        Long id,
        String profilePhoto,
        String description
){}
