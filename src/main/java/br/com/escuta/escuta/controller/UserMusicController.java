package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.response.MusicDetailsResponse;
import br.com.escuta.escuta.controller.response.MusicSumaryResponse;
import br.com.escuta.escuta.service.MusicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/me/musics")
@SecurityRequirement(name = "bearer-key")
public class UserMusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public MusicDetailsResponse musicCreation(@RequestBody @Valid MusicRequest request) {
        return musicService.musicCreation(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void musicResponse(@PathVariable Long id) {
        musicService.musicLogicalDelete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MusicDetailsResponse musicRequest(@PathVariable Long id) {
        return musicService.musics(id);
    }

    @GetMapping("/musics")
    @ResponseStatus(HttpStatus.OK)
    public Page<MusicSumaryResponse> musicRequest(@ParameterObject @PageableDefault(size = 20, sort = "releaseDate") Pageable pageable) {
        return musicService.listMusics(pageable);
    }
}
