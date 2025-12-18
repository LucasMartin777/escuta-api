package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.AlbumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {


    Optional<AlbumEntity> findByIdAndIsActiveTrue(Long id);

    Page<AlbumEntity> findAllByIsActiveTrue(Pageable pageable);

    @Query("select a from AlbumEntity a where a.id = ?1 and a.user.id = ?2")
    Optional<AlbumEntity> findByIdAndUser_Id(Long id, Long id1);
}
