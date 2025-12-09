package br.com.escuta.escuta.controller;


import br.com.escuta.escuta.controller.request.LoginAuthRequest;
import br.com.escuta.escuta.controller.request.LoginRegisterRequest;
import br.com.escuta.escuta.controller.response.LoginDetaisResponse;
import br.com.escuta.escuta.entity.LoginEntity;
import br.com.escuta.escuta.security.DadosTokenJWT;
import br.com.escuta.escuta.security.TokenService;
import br.com.escuta.escuta.service.UserLoginRegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")

public class AuthenticationController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UserLoginRegisterService userLoginRegisterService;

    @PostMapping("/login")
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid LoginAuthRequest request) {

        var token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((LoginEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)

    public LoginDetaisResponse efetuarLogin(
            @RequestBody @Valid LoginRegisterRequest request) {
        return userLoginRegisterService.register(request);
    }
}