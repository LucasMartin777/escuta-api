package br.com.escuta.escuta.controller;


import br.com.escuta.escuta.controller.request.UserLoginAuthRequest;
import br.com.escuta.escuta.controller.request.UserLoginRegisterRequest;
import br.com.escuta.escuta.controller.response.UserLoginDetaisResponse;
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
@RequestMapping("/auth")

public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserLoginRegisterService userLoginRegisterService;

    @PostMapping("/login")
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid UserLoginAuthRequest request) {

        try {

            var token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            var authentication = manager.authenticate(token);

            var tokenJWT = tokenService.gerarToken((UserLoginEntity) authentication.getPrincipal());

            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)

    public UserLoginDetaisResponse efetuarLogin(
            @RequestBody @Valid UserLoginRegisterRequest request) {
            return userLoginRegisterService.register(request);
    }

}