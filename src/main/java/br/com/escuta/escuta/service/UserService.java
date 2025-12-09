package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.UserUpdateRequest;
import br.com.escuta.escuta.controller.response.UserResponse;
import br.com.escuta.escuta.controller.response.UserSettingsResponse;
import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.mapper.UserMapper;
import br.com.escuta.escuta.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final LoginRepository repository;
    private final CreatePasswordService createPasswordService;

    public UserResponse perfilResponse(LoginEntity login) {
        LoginEntity user1 = repository.getReferenceById(login.getId());
        return UserMapper.toPerfilResponse(user1);
    }

    public UserSettingsResponse userSettingsResponse(LoginEntity login) {
        LoginEntity user1 = repository.getReferenceById(login.getId());
        return UserMapper.toPerfilSettingsResponse(user1);
    }

    public UserResponse userUpdate(LoginEntity login, UserUpdateRequest request) {

        String senhaCriptografada = createPasswordService.passwordEncoder(request.password());

        Optional.ofNullable(senhaCriptografada)
                .filter(n -> !n.isBlank())
                .ifPresent(login::setPassword);

        login.update(request);


        repository.save(login);

        return UserMapper.toPerfilResponse(login);
    }
}

