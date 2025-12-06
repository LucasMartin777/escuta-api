package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.UserUpdateRequest;
import br.com.escuta.escuta.controller.response.UserResponse;
import br.com.escuta.escuta.controller.response.UserSettingsResponse;
import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.mapper.UserMapper;
import br.com.escuta.escuta.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final LoginRepository repository;
    private final CreatePasswordService createPasswordService;

    public UserResponse userResponse(LoginEntity login) {
        LoginEntity user1 = repository.getReferenceById(login.getId());
        return UserMapper.toUserResponse(user1);
    }

    public UserSettingsResponse userSettingsResponse(LoginEntity login) {
        LoginEntity user1 = repository.getReferenceById(login.getId());
        return UserMapper.toUserSettingsResponse(user1.getUser());
    }

    @Transactional
    public UserSettingsResponse userUpdate(LoginEntity login, UserUpdateRequest request) {

        Optional.ofNullable(request.password())
                .filter(p -> !p.isBlank())
                .map(createPasswordService::passwordEncoder)
                .ifPresent(login::setPassword);

        login.getUser().update(request);

        return UserMapper.toUserSettingsResponse(login.getUser());
    }
}

