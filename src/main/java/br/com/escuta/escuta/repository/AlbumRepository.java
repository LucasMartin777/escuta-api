package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {

}
