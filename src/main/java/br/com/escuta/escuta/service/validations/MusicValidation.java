package br.com.escuta.escuta.service.validations;

import br.com.escuta.escuta.entity.MusicEntity;
import br.com.escuta.escuta.entity.UserEntity;
import br.com.escuta.escuta.service.OwnershipService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MusicValidation {

    private final OwnershipService ownershipService;

    public boolean isValidMusic(MusicEntity music, UserEntity user) {
        return music.getIsActive()
                && ownershipService.validateOwnershipMusic(music.getUser().getId(), user.getId());
    }

    public List<MusicEntity> filterValidMusics(List<MusicEntity> musics, UserEntity user) {
        return musics.stream()
                .filter(m -> isValidMusic(m, user))
                .toList();
    }
}
