package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.UserRegisterRequest;
import br.com.escuta.escuta.controller.response.UserRegisterResponse;
import br.com.escuta.escuta.controller.response.UserResponse;
import br.com.escuta.escuta.controller.response.UserSettingsResponse;
import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.entity.UserEntity;

import java.util.List;

public class UserMapper {

    public static UserEntity toEntity(UserRegisterRequest request) {
        return UserEntity.builder()

                .name(request.name())
                .dateOfBirth(request.dateOfBirth())
                .createdAt(request.createdAt())
                .build();
    }

    public static UserRegisterResponse toDetaislResponse(UserEntity userEntity) {
        return UserRegisterResponse.builder()
                .id(userEntity.getId())
                .profilePhoto(userEntity.getProfilePhoto())
                .description(userEntity.getDescription())
                .build();

    }

    public static UserResponse toUserResponse(LoginEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getName())
                .email(user.getEmail())
                .description(user.getUser().getDescription())
                .profilePhoto(user.getPerfil().getProfilePhoto())
                .playlists(user.getPlaylists() != null ?
                        user.getPlaylists().stream().map(PlaylistMapper::toDetailsResponse).toList()
                        : List.of())
                .musicas(user.getMusics() != null ?
                        user.getMusics().stream().map(MusicMapper::toDetailsResponse).toList()
                        : List.of())
                .albuns(user.getAlbums() != null ?
                        user.getAlbums().stream().map(AlbumMapper::toDetailsResponse).toList()
                        : List.of())
                .build();
    }
    public static UserSettingsResponse toUserSettingsResponse(UserLoginEntity user) {
        return UserSettingsResponse.builder()

                .userName(user.getName())
                .description(user.getPerfil().getDescription())
                .profilePhoto(user.getPerfil().getProfilePhoto())
                .build();
    }
}
