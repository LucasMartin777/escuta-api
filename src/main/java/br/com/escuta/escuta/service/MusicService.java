package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.response.MusicResponse;
import br.com.escuta.escuta.entity.AlbumEntity;
import br.com.escuta.escuta.entity.GenreEntity;
import br.com.escuta.escuta.entity.MusicEntity;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.mapper.MusicMapper;
import br.com.escuta.escuta.repository.AlbumRepository;
import br.com.escuta.escuta.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MusicService {

    private final AuthenticationUserService authenticationUserService;
    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;


    public MusicResponse musicCreation(MusicRequest request) {

        UserLoginEntity user = authenticationUserService.getAuthenticatedUser();

        GenreEntity genre = genreRepository.findById(request.genreId());

        AlbumEntity album = null;
        if (request.albumId() != null) {
            album = albumRepository.findById(request.albumId());
        }

        MusicEntity musicEntity = MusicMapper.toEntity(request);
        musicEntity.setAlbum(album);
        musicEntity.setUserLogin(user);
        musicEntity.setGenre(genre);


        return MusicMapper.toDetaislResponse(musicEntity);
    }
}
