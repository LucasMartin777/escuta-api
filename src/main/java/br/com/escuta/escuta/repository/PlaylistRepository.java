package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    @Query("select p from PlaylistEntity p where p.id = ?1 and p.user.id = ?2")
    Optional<PlaylistEntity> findByIdAndUser_Id(Long id, Long id1);
}
