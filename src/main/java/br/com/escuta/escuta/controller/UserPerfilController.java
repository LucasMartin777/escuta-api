package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.request.PerfilUpdateRequest;
import br.com.escuta.escuta.controller.response.UserPerfilResponse;
import br.com.escuta.escuta.controller.response.UserPerfilSettingsResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.service.UserPerfilService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users/me")
@SecurityRequirement(name = "bearer-key")
public class UserPerfilController {

    private final UserPerfilService userPerfilService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserPerfilResponse myProfile(@AuthenticationPrincipal UserLoginEntity user) {
        return userPerfilService.perfilResponse(user);
    }

    @GetMapping("/settings")
    @ResponseStatus(HttpStatus.OK)
    public UserPerfilSettingsResponse updateInformations(@AuthenticationPrincipal UserLoginEntity user) {
        return userPerfilService.perfilSettingsResponse(user);
    }

    @PatchMapping("/settings")
    @ResponseStatus(HttpStatus.OK)
    public UserPerfilResponse update(@AuthenticationPrincipal UserLoginEntity user,
                                     @RequestBody @Valid PerfilUpdateRequest perfilUpdateRequest) {
        return userPerfilService.perfilUpdate(user, perfilUpdateRequest);

    }
}
