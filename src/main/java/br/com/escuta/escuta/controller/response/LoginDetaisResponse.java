package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LoginDetaisResponse(

        Long id,
        String email,
        LocalDateTime createdAt,
        UserRegisterResponse userRegisterResponse

){}
