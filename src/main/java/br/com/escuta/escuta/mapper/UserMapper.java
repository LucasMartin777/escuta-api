package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.response.UserRegisterResponse;
import br.com.escuta.escuta.controller.response.UserResponse;
import br.com.escuta.escuta.controller.response.UserSettingsResponse;
import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.entity.UserEntity;

import java.util.List;

public class UserMapper {

    public static UserRegisterResponse toDetaislResponse(UserEntity userEntity) {
        return UserRegisterResponse.builder()
                .id(userEntity.getId())
                .profilePhoto(userEntity.getProfilePhoto())
                .description(userEntity.getDescription())
                .build();
    }

    public static UserResponse toUserResponse(LoginEntity login) {
        return UserResponse.builder()
                .id(login.getId())
                .userName(login.getUser().getName())
                .email(login.getEmail())
                .description(login.getUser().getDescription())
                .profilePhoto(login.getUser().getProfilePhoto())
                .playlists(
                        login.getUser().getPlaylists() == null
                                ? List.of()
                                : login.getUser().getPlaylists().stream()
                                .map(PlaylistMapper::toDetailsResponse)
                                .toList()
                )
                .musicas(
                        login.getUser().getMusics() == null
                                ? List.of()
                                : login.getUser().getMusics().stream()
                                .map(MusicMapper::toDetailsResponse)
                                .toList()
                )
                .albuns(
                        login.getUser().getAlbums() == null
                                ? List.of()
                                : login.getUser().getAlbums().stream()
                                .map(AlbumMapper::toDetailsResponse)
                                .toList()
                )
                .build();
    }

    public static UserSettingsResponse toUserSettingsResponse(UserEntity user) {
        return UserSettingsResponse.builder()
                .userName(user.getName())
                .description(user.getDescription())
                .profilePhoto(user.getProfilePhoto())
                .build();
    }
}
