package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.PerfilUpdateRequest;
import br.com.escuta.escuta.controller.response.UserPerfilResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.mapper.UserPerfilMapper;
import br.com.escuta.escuta.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPerfilService {

    @Autowired
    private AuthenticationUserService authenticationUserService;

    @Autowired
    private UserLoginRepository repository;

    @Autowired
    private OwnershipService ownershipService;

    @Autowired
    private CreatePasswordService createPasswordService;


    public UserPerfilResponse perfilResponse(@AuthenticationPrincipal UserLoginEntity user) {
        return UserPerfilMapper.toPerfilResponse(user);
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

