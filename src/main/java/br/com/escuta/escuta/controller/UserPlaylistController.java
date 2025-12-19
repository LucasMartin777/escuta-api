package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.request.PlaylistRequest;
import br.com.escuta.escuta.controller.response.PlaylistResponse;
import br.com.escuta.escuta.service.PlaylistService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users/me/playlists")
@SecurityRequirement(name = "bearer-key")
public class UserPlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistResponse create(@RequestBody @Valid PlaylistRequest request) {
        return playlistService.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        playlistService.logicalDelete(id);
    }
}
