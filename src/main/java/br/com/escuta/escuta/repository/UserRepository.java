package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


}

