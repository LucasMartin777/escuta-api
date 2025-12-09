package br.com.escuta.escuta.controller.request;

public record UserUpdateRequest(

        String name,
        String description,
        String password,
        String profilePhoto

){}
