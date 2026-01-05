package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.response.ProfileResponse;
import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/profiles")
@SecurityRequirement(name = "bearer-key")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse getProfile(@PathVariable Long id) {
        return profileService.getProfile(id);
    }

    @PostMapping("/{id}/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public void follow(@AuthenticationPrincipal LoginEntity login,
                       @PathVariable Long id) {
        profileService.follow(login.getId(), id);
    }

//    // 3. Ação de Parar de Seguir
//    @DeleteMapping("/{id}/follow") // Ou /unfollow, mas DELETE em /follow é bem RESTful
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void unfollow(@AuthenticationPrincipal LoginEntity login,
//                         @PathVariable Long id) {
//        followService.unfollow(login.getUser().getId(), id);
//    }
}