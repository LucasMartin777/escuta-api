package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PlaylistResponse(
        Long id,
        String name,
        String cover,
        boolean isPublic,
        LocalDate registrationDate
) {}
