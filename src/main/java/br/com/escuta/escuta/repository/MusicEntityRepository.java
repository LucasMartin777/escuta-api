package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicEntityRepository extends JpaRepository<MusicEntity, Long> {
    Page<MusicEntity> findAllByIsActiveTrue(Pageable pageable);

    Optional<MusicEntity> findByIdAndUserLogin_Id(Long id, Long id1);
}