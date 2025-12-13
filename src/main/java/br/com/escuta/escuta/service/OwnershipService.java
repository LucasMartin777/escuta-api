package br.com.escuta.escuta.service;

import org.springframework.stereotype.Service;

@Service
public class OwnershipService {

    public boolean validateOwnershipMusic(Long ownerId, Long loggedUserId) {

        return ownerId.equals(loggedUserId);
    }
}
