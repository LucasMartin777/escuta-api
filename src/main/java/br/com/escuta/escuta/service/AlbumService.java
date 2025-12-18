package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.AlbumRequest;
import br.com.escuta.escuta.controller.request.AlbumUpdateRequest;
import br.com.escuta.escuta.controller.response.AlbumDetailsResponse;
import br.com.escuta.escuta.controller.response.AlbumSumaryResponse;
import br.com.escuta.escuta.entity.AlbumEntity;
import br.com.escuta.escuta.entity.MusicEntity;
import br.com.escuta.escuta.entity.UserEntity;
import br.com.escuta.escuta.mapper.AlbumMapper;
import br.com.escuta.escuta.repository.AlbumRepository;
import br.com.escuta.escuta.repository.LoginRepository;
import br.com.escuta.escuta.repository.MusicRepository;
import br.com.escuta.escuta.service.validations.MusicValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AuthenticationUserService authenticationUserService;
    private final AlbumRepository albumRepository;
    private final MusicRepository musicRepository;
    private final LoginRepository loginRepository;
    private final MusicValidation musicValidation;
    private final OwnershipService ownershipService;

    public AlbumDetailsResponse album(Long id) {

        AlbumEntity album = albumRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Album não encontrado"));

        return AlbumMapper.toDetailsResponse(album);
    }

    public Page<AlbumSumaryResponse> listAlbums(Pageable pageable) {

        return albumRepository.findAllByIsActiveTrue(pageable)
                .map(AlbumMapper::toSumaryResponse);
    }

    @Transactional
    public AlbumDetailsResponse create(AlbumRequest request) {

        Long loginId = authenticationUserService.getAuthenticatedUserId();
        UserEntity user = loginRepository.getReferenceById(loginId).getUser();

        List<MusicEntity> musics = musicRepository.findAllById(request.music());

        List<MusicEntity> validMusics = musicValidation.filterValidMusics(musics, user);

        AlbumEntity album = AlbumMapper.toEntity(request, user);

        albumRepository.save(album);

        validMusics.forEach(m -> m.setAlbum(album));
        musicRepository.saveAll(validMusics);

        return AlbumMapper.toDetailsResponse(album);
    }

    @Transactional
    public AlbumDetailsResponse update(Long id, AlbumUpdateRequest request) {

        Long authenticatedUserId =
                authenticationUserService.getAuthenticatedUserId();

        AlbumEntity album = albumRepository.findByIdAndUser_Id(id, authenticatedUserId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Álbum não encontrado para esse usuário"));

        // 🔹 IDs enviados (remove duplicados)
        Set<Long> requestedMusicIds = new HashSet<>(request.music());

        // 🔹 Músicas atuais do álbum
        List<MusicEntity> currentMusics =
                musicRepository.findByAlbum_Id(album.getId());

        Set<Long> currentMusicIds = currentMusics.stream()
                .map(MusicEntity::getId)
                .collect(Collectors.toSet());

        // 🔹 Buscar músicas do request
        List<MusicEntity> requestedMusics =
                musicRepository.findAllById(requestedMusicIds);

        // 🔹 Validação de ownership / regras
        List<MusicEntity> validMusics =
                musicValidation.filterValidMusics(requestedMusics, album.getUser());

        // ➕ Músicas novas
        List<MusicEntity> musicsToAdd = validMusics.stream()
                .filter(m -> !currentMusicIds.contains(m.getId()))
                .toList();

        // ❌ Músicas removidas
        List<MusicEntity> musicsToRemove = currentMusics.stream()
                .filter(m -> !requestedMusicIds.contains(m.getId()))
                .toList();

        // ➕ Vincula novas
        musicsToAdd.forEach(m -> m.setAlbum(album));

        // ❌ Remove vínculo (ON DELETE SET NULL)
        musicsToRemove.forEach(m -> m.setAlbum(null));

        // 🔹 Atualiza dados do álbum
        album.update(request);

        return AlbumMapper.toDetailsResponse(album);
    }

    @Transactional
    public void delete(Long id) {

        Long authenticatedUserId = authenticationUserService.getAuthenticatedUserId();

        AlbumEntity album = albumRepository.findByIdAndUser_Id(id, authenticatedUserId)
                .orElseThrow(() -> new EntityNotFoundException("Album não encontrada para esse usuario"));

        album.logicalExclusion();

    }
}
