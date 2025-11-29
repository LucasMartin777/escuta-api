package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicEntityRepository extends JpaRepository<MusicEntity, Long> {
}