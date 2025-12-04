package br.com.escuta.escuta.controller.request;

public record PerfilUpdateRequest(

        String userName,
        String description,
        String password,
        String profilePhoto

){}
