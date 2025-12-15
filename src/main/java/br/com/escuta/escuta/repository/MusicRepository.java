package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<MusicEntity, Long> {
    Page<MusicEntity> findAllByIsActiveTrue(Pageable pageable);

    @Query("select m from MusicEntity m where m.id = ?1 and m.user.id = ?2")
    Optional<MusicEntity> findByIdAndUser_Id(Long id, Long id1);
}