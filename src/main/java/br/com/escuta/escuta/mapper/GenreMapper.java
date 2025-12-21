package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.GenreRequest;
import br.com.escuta.escuta.controller.response.GenreResponse;
import br.com.escuta.escuta.entity.GenreEntity;

public class GenreMapper {
    public static GenreEntity toEntity(GenreRequest request) {
        return GenreEntity.builder()
                .name(request.name())
                .build();
    }

    public static GenreResponse toDetailsResponse(GenreEntity genre) {
        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
