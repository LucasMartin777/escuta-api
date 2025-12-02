package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.response.MusicResponse;
import br.com.escuta.escuta.service.MusicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me/music")
@SecurityRequirement(name = "bearer-key")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping("upload")
    @ResponseStatus(HttpStatus.CREATED)
    public MusicResponse musicCreation(@RequestBody @Valid MusicRequest request) {
        return musicService.musicCreation(request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void musicResponse(@PathVariable Long id) {
        musicService.musicLogicalDelete(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MusicResponse musicRequest(@PathVariable  Long id){
        return musicService.musics(id);
    }
}
