package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.request.MusicUpdateRequest;
import br.com.escuta.escuta.controller.response.MusicDetailsResponse;
import br.com.escuta.escuta.controller.response.MusicSumaryResponse;
import br.com.escuta.escuta.entity.AlbumEntity;
import br.com.escuta.escuta.entity.GenreEntity;
import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.entity.MusicEntity;
import br.com.escuta.escuta.mapper.MusicMapper;
import br.com.escuta.escuta.repository.AlbumRepository;
import br.com.escuta.escuta.repository.GenreRepository;
import br.com.escuta.escuta.repository.LoginRepository;
import br.com.escuta.escuta.repository.MusicEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MusicService {

    private final AuthenticationUserService authenticationUserService;
    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;
    private final MusicEntityRepository musicEntityRepository;
    private final LoginRepository loginRepository;
    private final OwnershipService ownershipService;

    @Transactional
    public MusicDetailsResponse create(MusicRequest request) {

        Long userId = authenticationUserService.getAuthenticatedUserId();
        LoginEntity user = loginRepository.getReferenceById(userId);


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
    public void logicalDelete(Long id) {

        Long userAuthenticated = authenticationUserService.getAuthenticatedUserId();

        MusicEntity musicEntity = musicEntityRepository.getReferenceById(id);

        ownershipService.validateOwnershipMusic(
                musicEntity.getUserLogin().getId(),
                userAuthenticated
        );
        musicEntity.logicalExclusion();
    }

    public MusicDetailsResponse musics(Long id) {

        MusicEntity music = musicEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Musica nao encontrada"));
        return MusicMapper.toDetailsResponse(music);
    }

    public Page<MusicSumaryResponse> listMusics(Pageable pageable) {

        return musicEntityRepository.findAllByIsActiveTrue(pageable)
                .map(MusicMapper::toSumaryResponse);
    }

    @Transactional
    public MusicDetailsResponse update(Long id, MusicUpdateRequest request) {

        Long userAuthenticated = authenticationUserService.getAuthenticatedUserId();

        MusicEntity music = musicEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));

        ownershipService.validateOwnershipMusic(
                music.getUserLogin().getId(),
                userAuthenticated
        );

        music.update(request);

        Optional.ofNullable(request.genreId())
                .ifPresent(genreId -> {
                    GenreEntity genre = genreRepository.findById(genreId)
                            .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));
                    music.setGenre(genre);
                });

        musicEntityRepository.save(music);
        return MusicMapper.toDetailsResponse(music);
    }
}
