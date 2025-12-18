package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.request.AlbumRequest;
import br.com.escuta.escuta.controller.request.AlbumUpdateRequest;
import br.com.escuta.escuta.controller.response.AlbumDetailsResponse;
import br.com.escuta.escuta.service.AlbumService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/album/me/musics")
@SecurityRequirement(name = "bearer-key")
public class UserAlbumController {

    private final AlbumService albumService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumDetailsResponse create(@RequestBody @Valid AlbumRequest request) {
        return albumService.create(request);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumDetailsResponse update(@RequestBody @Valid AlbumUpdateRequest request,
                                       @PathVariable Long id) {
        return albumService.update(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        albumService.delete(id);
    }
}
