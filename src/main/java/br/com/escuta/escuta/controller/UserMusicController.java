package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.request.MusicUpdateRequest;
import br.com.escuta.escuta.controller.response.MusicDetailsResponse;
import br.com.escuta.escuta.service.MusicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users/me/musics")
@SecurityRequirement(name = "bearer-key")
public class UserMusicController {

    private final MusicService musicService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicDetailsResponse create(@RequestBody @Valid MusicRequest request) {
        return musicService.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        musicService.logicalDelete(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MusicDetailsResponse update(@PathVariable Long id,
                                       @RequestBody MusicUpdateRequest musicUpdateRequest) {
        return musicService.update(id, musicUpdateRequest);
    }
}
