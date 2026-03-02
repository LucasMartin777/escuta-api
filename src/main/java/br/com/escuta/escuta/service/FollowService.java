package br.com.escuta.escuta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class FollowService {

    private final AuthenticationUserService authenticationUserService;
}
