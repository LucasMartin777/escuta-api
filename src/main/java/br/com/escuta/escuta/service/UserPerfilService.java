package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.PerfilUpdateRequest;
import br.com.escuta.escuta.controller.response.UserPerfilResponse;
import br.com.escuta.escuta.mapper.UserPerfilMapper;
import br.com.escuta.escuta.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPerfilService {

    @Autowired
    private AuthenticationUserService authenticationUserService;

    @Autowired
    private UserLoginRepository repository;


    public UserPerfilResponse perfilResponse(Long id) {
        var user = repository.findById(id).orElseThrow();
        return UserPerfilMapper.toPerfilResponse(user);
    }

//    public UserPerfilResponse perfilUpdate(Long id, PerfilUpdateRequest request) {
//        authenticationUserService.getAuthenticatedUserId()
//    }
}

