package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}
