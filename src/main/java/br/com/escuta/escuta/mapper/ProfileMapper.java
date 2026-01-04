package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.response.ProfileResponse;
import br.com.escuta.escuta.controller.response.UserResponse;
import br.com.escuta.escuta.entity.UserEntity;

import java.util.List;

public class ProfileMapper {

    public static ProfileResponse toUserResponse(UserEntity userEntity) {
        return ProfileResponse.builder()
                .id(userEntity.getId())
                .userName(userEntity.getName())
                .description(userEntity.getDescription())
                .profilePhoto(userEntity.getProfilePhoto())
                .playlists(
                        userEntity.getPlaylists() == null
                                ? List.of()
                                : userEntity.getPlaylists().stream()
                                .map(PlaylistMapper::toDetailsResponse)
                                .toList()
                )
                .musicas(
                        userEntity.getMusics() == null
                                ? List.of()
                                : userEntity.getMusics().stream()
                                .map(MusicMapper::toSumaryResponse)
                                .toList()
                )
                .albuns(
                        userEntity.getAlbums() == null
                                ? List.of()
                                : userEntity.getAlbums().stream()
                                .map(AlbumMapper::toSumaryResponse)
                                .toList()
                )
                .build();
    }
}

