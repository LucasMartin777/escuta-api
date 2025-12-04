package br.com.escuta.escuta.controller.response;

import lombok.Builder;

@Builder
public record UserPerfilSettingsResponse(

        String userName,
        String description,
        String password,
        String profilePhoto
) {
}
