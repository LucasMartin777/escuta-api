package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


}

