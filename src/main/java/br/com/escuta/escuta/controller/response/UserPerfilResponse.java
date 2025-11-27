package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.util.List;

@Builder
public record UserPerfilResponse(
        Long id,
        String userName,
        String email,
        String profilePhoto,
        String description,
        List<PlaylistResponse> playlists,
        List<MusicResponse> musicas,
        List<AlbumResponse> albuns
) {
}
