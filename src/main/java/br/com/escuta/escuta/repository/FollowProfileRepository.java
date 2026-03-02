package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.FollowProfileEntity;
import br.com.escuta.escuta.entity.FollowProfileIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowProfileRepository extends JpaRepository<FollowProfileEntity, FollowProfileIdEntity> {


    boolean existsById(FollowProfileIdEntity id);


    Optional<FollowProfileEntity> findById(FollowProfileIdEntity id);
}