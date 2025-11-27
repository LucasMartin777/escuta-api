package br.com.escuta.escuta.mapper;

import br.com.escuta.escuta.controller.request.UserPerfilRegisterRequest;
import br.com.escuta.escuta.controller.response.UserPerfilRegisterResponse;
import br.com.escuta.escuta.controller.response.UserPerfilResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.entity.UserPerfilEntity;

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
                .playlists(user.getPlaylists().stream().map(PlaylistMapper::toDetailsResponse).toList())
                .musicas(user.getMusics().stream().map(MusicMapper::toDetaislResponse).toList())
                .albuns(user.getAlbums().stream().map(AlbumMapper::toDetailsResponse).toList())
                .build();
    }
}
