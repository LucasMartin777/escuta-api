package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.PlaylistRequest;
import br.com.escuta.escuta.controller.response.PlaylistResponse;
import br.com.escuta.escuta.entity.MusicEntity;
import br.com.escuta.escuta.entity.PlaylistEntity;
import br.com.escuta.escuta.entity.UserEntity;
import br.com.escuta.escuta.mapper.PlaylistMapper;
import br.com.escuta.escuta.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PlaylistService {

    private final AuthenticationUserService authenticationUserService;
    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;
    private final MusicRepository musicRepository;
    private final LoginRepository loginRepository;
    private final OwnershipService ownershipService;
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    @Transactional
    public PlaylistResponse create(PlaylistRequest request) {

        Long userId = authenticationUserService.getAuthenticatedUserId();
        UserEntity user = userRepository.getReferenceById(userId);

        PlaylistEntity playlist = PlaylistMapper.toEntity(request);
        playlist.setUser(user);

        if (request.music() != null && !request.music().isEmpty()) {

            List<Long> musicIds = request.music().stream()
                    .filter(Objects::nonNull)
                    .toList();

            List<MusicEntity> musics = musicRepository.findAllById(musicIds);
            playlist.setMusic(musics);
        }

        PlaylistEntity saved = playlistRepository.save(playlist);

        return PlaylistMapper.toDetailsResponse(saved);
    }


    @Transactional
    public void logicalDelete(Long id) {

        Long authenticatedUserId = authenticationUserService.getAuthenticatedUserId();

        PlaylistEntity playlistEntity = playlistRepository.findByIdAndUser_Id(id, authenticatedUserId)
                .orElseThrow(() -> new EntityNotFoundException("Album não encontrada para esse usuario"));

        playlistEntity.logicalExclusion();

    }
}
