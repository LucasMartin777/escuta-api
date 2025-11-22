package br.com.escuta.escuta.controller;


import br.com.escuta.escuta.controller.request.UserLoginRequest;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.security.DadosTokenJWT;
import br.com.escuta.escuta.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")

public class AuthenticationController {

    @Autowired
    private  AuthenticationManager manager;

    @Autowired
    private  TokenService tokenService;

    @PostMapping()

    public ResponseEntity efetuarLogin(@RequestBody @Valid UserLoginRequest request) {
        try {
                var token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
                var authentication = manager.authenticate(token);

                var tokenJWT = tokenService.gerarToken((UserLoginEntity) authentication.getPrincipal());

                return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }
}

