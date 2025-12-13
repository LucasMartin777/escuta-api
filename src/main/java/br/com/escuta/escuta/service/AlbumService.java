package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.AlbumRequest;
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

import java.util.List;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AuthenticationUserService authenticationUserService;
    private final AlbumRepository albumRepository;
    private final MusicRepository musicRepository;
    private final LoginRepository loginRepository;
    private final MusicValidation musicValidation;

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

        Long loginId = authenticationUserService.getAuthenticatedLoginId();
        UserEntity user = loginRepository.getReferenceById(loginId).getUser();

        List<MusicEntity> musics = musicRepository.findAllById(request.music());

        List<MusicEntity> validMusics = musicValidation.filterValidMusics(musics, user);

        AlbumEntity album = AlbumMapper.toEntity(request, user);

        albumRepository.save(album);

        validMusics.forEach(m -> m.setAlbum(album));
        musicRepository.saveAll(validMusics);

        return AlbumMapper.toDetailsResponse(album);

    }
}
