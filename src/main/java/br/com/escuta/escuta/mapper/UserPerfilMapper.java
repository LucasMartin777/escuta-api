package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.UserPerfilRegisterRequest;
import br.com.escuta.escuta.controller.response.UserPerfilRegisterResponse;
import br.com.escuta.escuta.controller.response.UserPerfilResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.entity.UserPerfilEntity;

import java.util.List;

public class UserPerfilMapper {

    public static UserPerfilEntity toEntity(UserPerfilRegisterRequest request) {
        return UserPerfilEntity.builder()
                .profilePhoto(request.profile_photo())
                .description(request.description())
                .build();
    }

    public static UserPerfilRegisterResponse toDetaislResponse(UserPerfilEntity userPerfilEntity) {
        return UserPerfilRegisterResponse.builder()
                .id(userPerfilEntity.getId())
                .profilePhoto(userPerfilEntity.getProfilePhoto())
                .description(userPerfilEntity.getDescription())
                .build();

    }

    public static UserPerfilResponse toPerfilResponse(UserLoginEntity user) {
        return UserPerfilResponse.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .description(user.getPerfil().getDescription())
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
}
