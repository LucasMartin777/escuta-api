package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.UserLoginEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Long> {
    @EntityGraph(attributePaths = "perfil")
    Optional<UserLoginEntity> findByEmail(String email);

    @EntityGraph(attributePaths = "perfil")
    Optional<UserLoginEntity> findEntityByEmail(String email);


}
