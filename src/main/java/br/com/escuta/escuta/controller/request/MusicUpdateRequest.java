package br.com.escuta.escuta.controller.request;

public record MusicUpdateRequest(
        String name,
        String singleCover,
        Long genreId
) {}
