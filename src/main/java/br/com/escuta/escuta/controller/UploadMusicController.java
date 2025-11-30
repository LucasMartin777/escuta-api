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
@RequestMapping("/me/music-update")
@SecurityRequirement(name = "bearer-key")
public class UploadMusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicResponse musicCreation(@RequestBody @Valid MusicRequest request) {
        try {
            return musicService.musicCreation(request);

        } catch (IllegalArgumentException e) {
            // Erros conhecidos: entidade não encontrada, dados inválidos, etc.
            throw new RuntimeException("Erro ao criar música: " + e.getMessage());

        }
    }
}
