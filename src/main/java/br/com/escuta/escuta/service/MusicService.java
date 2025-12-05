package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.request.MusicUpdateRequest;
import br.com.escuta.escuta.controller.response.MusicDetailsResponse;
import br.com.escuta.escuta.controller.response.MusicSumaryResponse;
import br.com.escuta.escuta.entity.*;
import br.com.escuta.escuta.mapper.MusicMapper;
import br.com.escuta.escuta.repository.*;
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
    private final UserRepository userRepository;

    @Transactional
    public MusicDetailsResponse create(MusicRequest request) {

        Long userId = authenticationUserService.getAuthenticatedLoginId();
        LoginEntity userLogin = loginRepository.getReferenceById(userId);
        UserEntity user = userRepository.getReferenceById(userLogin.getId());


        GenreEntity genre = genreRepository.findById(request.genreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));


        AlbumEntity album = null;
        if (request.albumId() != null) {
            album = albumRepository.findById(request.albumId())
                    .orElseThrow(() -> new RuntimeException("Album not found"));
        }


        MusicEntity musicEntity = MusicMapper.toEntity(request);
        musicEntity.setAlbum(album);
        musicEntity.setUsers(user);
        musicEntity.setGenres(genre);

        musicEntityRepository.save(musicEntity);


        return MusicMapper.toDetailsResponse(musicEntity);
    }

    @Transactional
    public void logicalDelete(Long id) {

        Long loginId = authenticationUserService.getAuthenticatedLoginId();

        LoginEntity userLogin = loginRepository.getReferenceById(loginId);
        UserEntity user = userLogin.getUser();

        MusicEntity musicEntity = musicEntityRepository.getReferenceById(id);

        ownershipService.validateOwnershipMusic(
                musicEntity.getUsers().getId(),
                user.getId()
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

        Long loginId = authenticationUserService.getAuthenticatedLoginId();

        LoginEntity userLogin = loginRepository.getReferenceById(loginId);
        UserEntity user = userLogin.getUser();

        MusicEntity musicEntity = musicEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));

        ownershipService.validateOwnershipMusic(
                musicEntity.getUsers().getId(),
                user.getId()
        );

        musicEntity.update(request);

        Optional.ofNullable(request.genreId())
                .ifPresent(genreId -> {
                    GenreEntity genre = genreRepository.findById(genreId)
                            .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));
                    musicEntity.setGenres(genre);
                });

        return MusicMapper.toDetailsResponse(musicEntity);
    }
}
