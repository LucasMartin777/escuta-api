package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<MusicEntity, Long> {
    Page<MusicEntity> findAllByIsActiveTrue(Pageable pageable);
}