package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.FollowProfileEntity;
import br.com.escuta.escuta.entity.FollowProfileIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowProfileRepository extends JpaRepository<FollowProfileEntity, FollowProfileIdEntity> {

    // AJUSTE: Troque 'TargetId' por 'FollowedId'
    long countByIdFollowedId(Long followedId);

    // Verifique se este também está correto de acordo com sua classe de ID
    long countByIdFollowerId(Long followerId);
}