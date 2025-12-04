package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.PerfilUpdateRequest;
import br.com.escuta.escuta.controller.response.UserPerfilResponse;
import br.com.escuta.escuta.controller.response.UserPerfilSettingsResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.mapper.UserPerfilMapper;
import br.com.escuta.escuta.repository.UserLoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPerfilService {

    private final UserLoginRepository repository;
    private final CreatePasswordService createPasswordService;

    public UserPerfilResponse perfilResponse(UserLoginEntity user) {
        UserLoginEntity user1 = repository.getReferenceById(user.getId());
        return UserPerfilMapper.toPerfilResponse(user1);
    }

    public UserPerfilSettingsResponse perfilSettingsResponse(UserLoginEntity user) {
        UserLoginEntity user1 = repository.getReferenceById(user.getId());
        return UserPerfilMapper.toPerfilSettingsResponse(user1);
    }

    public UserPerfilResponse perfilUpdate(UserLoginEntity user, PerfilUpdateRequest request) {

        String senhaCriptografada = createPasswordService.passwordEncoder(request.password());

        Optional.ofNullable(senhaCriptografada)
                .filter(n -> !n.isBlank())
                .ifPresent(user::setPassword);

        user.update(request);

        repository.save(user);

        return UserPerfilMapper.toPerfilResponse(user);
    }
}

