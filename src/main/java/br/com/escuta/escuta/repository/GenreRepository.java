package br.com.escuta.escuta.repository;

import br.com.escuta.escuta.entity.GenreEntity;
import jakarta.validation.constraints.NotNull;


public interface GenreRepository {
    GenreEntity findById(@NotNull Long aLong);
}
