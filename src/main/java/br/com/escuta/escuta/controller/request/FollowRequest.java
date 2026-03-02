package br.com.escuta.escuta.controller.request;

import jakarta.validation.constraints.NotNull;

public record FollowRequest(
        @NotNull
        Long followingId
) {
}
