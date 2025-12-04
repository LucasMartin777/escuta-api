package br.com.escuta.escuta.controller;

import br.com.escuta.escuta.controller.response.AlbumDetailsResponse;
import br.com.escuta.escuta.controller.response.AlbumSumaryResponse;
import br.com.escuta.escuta.service.AlbumService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/albums")
@SecurityRequirement(name = "bearer-key")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumDetailsResponse getAlbumById(@PathVariable Long id) {
        return albumService.album(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<AlbumSumaryResponse> listUserAlbums(@ParameterObject @PageableDefault(size = 20, sort = "releaseDate") Pageable pageable) {
        return albumService.listAlbums(pageable);
    }
}
