package br.com.escuta.escuta.controller.request;

import java.util.List;

public record PlaylistUpdateRequest(

        String name,

        Boolean isPublic,

        String cover,

        List<Long> music
) {
}
