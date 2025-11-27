package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.AlbumEntity;


public interface AlbumRepository {
    AlbumEntity findById(Long aLong);
}
