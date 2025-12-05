package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.request.UserUpdateRequest;
import br.com.escuta.escuta.controller.response.UserResponse;
import br.com.escuta.escuta.controller.response.UserSettingsResponse;
import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.service.UserService;
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
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse myProfile(@AuthenticationPrincipal LoginEntity user) {
        return userService.userResponse(user);
    }

    @GetMapping("/settings")
    @ResponseStatus(HttpStatus.OK)
    public UserSettingsResponse updateInformations(@AuthenticationPrincipal LoginEntity user) {
        return userService.userSettingsResponse(user);
    }

    @PatchMapping("/settings")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse update(@AuthenticationPrincipal LoginEntity user,
                               @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return userService.userUpdate(user, userUpdateRequest);

    }
}
