package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.AlbumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {


    Optional<AlbumEntity> findByIdAndIsActiveTrue(Long id);

    Page<AlbumEntity> findAllByIsActiveTrue(Pageable pageable);
}
