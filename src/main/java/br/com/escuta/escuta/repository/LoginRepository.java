package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.LoginEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    @EntityGraph(attributePaths = "user")
    Optional<LoginEntity> findByEmail(String email);

    @EntityGraph(attributePaths = "user")
    Optional<LoginEntity> findEntityByEmail(String email);


}
