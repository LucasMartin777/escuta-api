package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder

public record ErrorResponse(

        LocalDateTime timestamp,
        int status,
        String error,
        Object message,
        String path) {

}

