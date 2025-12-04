package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.request.PerfilUpdateRequest;
import br.com.escuta.escuta.controller.response.UserPerfilResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.service.UserPerfilService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/me/settings")
@SecurityRequirement(name = "bearer-key")
public class UserPerfilSettingsController {

    @Autowired
    UserPerfilService userPerfilService;

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public UserPerfilResponse update(@AuthenticationPrincipal UserLoginEntity user,
                                     @RequestBody @Valid PerfilUpdateRequest perfilUpdateRequest) {
        return userPerfilService.perfilUpdate(user, perfilUpdateRequest);

    }
}
