package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.service.FollowService;
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
    private final FollowService followService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse getProfile(@AuthenticationPrincipal LoginEntity login,
                                      @PathVariable Long id) {
        // Passamos o ID do logado para saber se 'EU' sigo 'ELE'
        return profileService.getPublicProfile(id, login.getUser().getId());
    }

    // 2. Ação de Seguir
    @PostMapping("/{id}/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public void follow(@AuthenticationPrincipal LoginEntity login,
                       @PathVariable Long id) {
        followService.follow(login.getUser().getId(), id);
    }

    // 3. Ação de Parar de Seguir
    @DeleteMapping("/{id}/follow") // Ou /unfollow, mas DELETE em /follow é bem RESTful
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollow(@AuthenticationPrincipal LoginEntity login,
                         @PathVariable Long id) {
        followService.unfollow(login.getUser().getId(), id);
    }
}