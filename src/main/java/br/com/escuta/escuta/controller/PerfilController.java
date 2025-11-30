package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.response.UserPerfilResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.service.UserPerfilService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me/perfil")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {


    @Autowired
    UserPerfilService userPerfilService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public UserPerfilResponse getMeuPerfil(@AuthenticationPrincipal UserLoginEntity user) {
        return userPerfilService.perfilResponse(user.getId());
    }
}
