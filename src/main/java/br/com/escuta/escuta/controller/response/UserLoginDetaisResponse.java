package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;
@Builder
public record UserLoginDetaisResponse(
        Long id,
        String userName,
        String email,
        String cpf,
        Date dateOfBirth,
        LocalDateTime createdAt,
        UserPerfilRegisterResponse perfil
) {
}
