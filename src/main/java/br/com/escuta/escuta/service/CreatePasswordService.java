package br.com.escuta.escuta.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePasswordService {
    private final PasswordEncoder passwordEncoder;

    public String passwordEncoder(String password) {
        return passwordEncoder.encode(password);
    }
}
