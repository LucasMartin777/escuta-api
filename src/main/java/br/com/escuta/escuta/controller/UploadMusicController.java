package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.request.MusicRequest;
import br.com.escuta.escuta.controller.response.MusicResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.service.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me/music-update")
public class UploadMusicController {

    @Autowired
    private MusicService musicService;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicResponse musicCreation(@RequestBody @Valid MusicRequest request) {
        return musicService.musicCreation(request);
    }
}
