package br.com.escuta.escuta.controller.response;

import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(

        Long id,
        String userName,
        String email,
        String profilePhoto,
        String description,
        List<PlaylistResponse> playlists,
        List<MusicSumaryResponse> musicas,
        List<AlbumSumaryResponse> albuns
){}
