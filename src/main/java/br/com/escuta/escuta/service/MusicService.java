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
import br.com.escuta.escuta.repository.MusicEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MusicService {

    @Autowired
    private AuthenticationUserService authenticationUserService;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private MusicEntityRepository musicEntityRepository;


    public MusicResponse musicCreation(MusicRequest request) {

        UserLoginEntity user = authenticationUserService.getAuthenticatedUser();

        GenreEntity genre = genreRepository.findById(request.genreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));


        AlbumEntity album = null;
        if (request.albumId() != null) {
            album = albumRepository.findById(request.albumId())
                    .orElseThrow(() -> new RuntimeException("Album not found"));
        }


        MusicEntity musicEntity = MusicMapper.toEntity(request);
        musicEntity.setAlbum(album);
        musicEntity.setUserLogin(user);
        musicEntity.setGenre(genre);

        musicEntityRepository.save(musicEntity);


        return MusicMapper.toDetaislResponse(musicEntity);
    }

    public void musicLogicalDelete(Long id) {
        MusicEntity musicEntity = musicEntityRepository.getReferenceById(id);
        musicEntity.logicalExclusion(musicEntity);
    }
}
