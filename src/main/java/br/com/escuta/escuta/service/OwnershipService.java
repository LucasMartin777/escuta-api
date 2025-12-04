package br.com.escuta.escuta.service;

import org.springframework.stereotype.Service;

@Service
public class OwnershipService {

    public void validateOwnership(Long musicOwnerId, Long loggedUserId)
    {
        if (!musicOwnerId.equals(loggedUserId)) {
            throw new SecurityException("Você não tem permissão para acessar ou alterar este recurso.");
        }
    }
}