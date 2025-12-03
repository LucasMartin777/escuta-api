package br.com.escuta.escuta.controller;


import br.com.escuta.escuta.controller.request.UserLoginAuthRequest;
import br.com.escuta.escuta.controller.request.UserLoginRegisterRequest;
import br.com.escuta.escuta.controller.response.UserLoginRegisterResponse;
import br.com.escuta.escuta.entity.UserLoginEntity;
import br.com.escuta.escuta.security.DadosTokenJWT;
import br.com.escuta.escuta.security.TokenService;
import br.com.escuta.escuta.service.UserLoginRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-auth")

public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserLoginRegisterService userLoginRegisterService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid UserLoginAuthRequest request) {

        var token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((UserLoginEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserLoginRegisterResponse efetuarLogin(
            @RequestBody @Valid UserLoginRegisterRequest request) {
        return userLoginRegisterService.register(request);
    }

}