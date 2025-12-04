package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.response.AlbumDetailsResponse;
import br.com.escuta.escuta.controller.response.AlbumSumaryResponse;
import br.com.escuta.escuta.entity.AlbumEntity;
import br.com.escuta.escuta.mapper.AlbumMapper;
import br.com.escuta.escuta.repository.AlbumRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumDetailsResponse album(Long id) {

        AlbumEntity album = albumRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Album não encontrado"));
        return AlbumMapper.toDetailsResponse(album);
    }

    public Page<AlbumSumaryResponse> listAlbums(Pageable pageable) {

        return albumRepository.findAllByIsActiveTrue(pageable)
                .map(AlbumMapper::toSumaryResponse);

    }
}
