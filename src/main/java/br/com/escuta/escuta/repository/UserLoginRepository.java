package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Long> {
    UserDetails findByEmail(String email);
}
