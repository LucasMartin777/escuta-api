package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.response.MusicDetailsResponse;
import br.com.escuta.escuta.controller.response.MusicSumaryResponse;
import br.com.escuta.escuta.entity.AlbumEntity;
import br.com.escuta.escuta.entity.GenreEntity;
import br.com.escuta.escuta.entity.MusicEntity;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.mapper.MusicMapper;
import br.com.escuta.escuta.repository.AlbumRepository;
import br.com.escuta.escuta.repository.GenreRepository;
import br.com.escuta.escuta.repository.MusicEntityRepository;
import br.com.escuta.escuta.repository.UserLoginRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private UserLoginRepository userLoginRepository;

    @Transactional
    public MusicDetailsResponse musicCreation(MusicRequest request) {

        Long userId = authenticationUserService.getAuthenticatedUserId();
        UserLoginEntity user = userLoginRepository.getReferenceById(userId);


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


        return MusicMapper.toDetailsResponse(musicEntity);
    }

    @Transactional
    public void musicLogicalDelete(Long id) {

        Long userAuthenticated = authenticationUserService.getAuthenticatedUserId();

        MusicEntity musicEntity = musicEntityRepository.getReferenceById(id);

        boolean comparemusic = musicEntity.getUserLogin().getId().equals(userAuthenticated);

        if (!comparemusic) {
            throw new SecurityException("Essa musica nao pertence ao seu usuario e nao pode ser deletada");
        }
        musicEntity.logicalExclusion();
    }


//    @Transactional
//    public void musicLogicalDelete3(Long id) {
//
//        Long userAuthenticatedId = authenticationUserService.getAuthenticatedUserId();
//
//        var music = musicEntityRepository.findByIdAndUserLogin_Id(id, userAuthenticatedId)
//                .orElseThrow(() -> new IllegalArgumentException("Musica nao encontrada para o usuario logado"));
//
//        music.logicalExclusion();
//    }

    public MusicDetailsResponse musics(Long id) {

        MusicEntity music = musicEntityRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Musica nao encontrada"));
        return MusicMapper.toDetailsResponse(music);
    }

    public Page<MusicSumaryResponse> listMusics(Pageable pageable) {

        return musicEntityRepository.findAllByIsActiveTrue(pageable)
                .map(MusicMapper::toSumaryResponse);
    }
}
