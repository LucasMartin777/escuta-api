package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.LoginRegisterRequest;
import br.com.escuta.escuta.controller.response.LoginDetaisResponse;
import br.com.escuta.escuta.entity.UserEntity;
import br.com.escuta.escuta.mapper.LoginMapper;
import br.com.escuta.escuta.repository.LoginRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserLoginRegisterService {

    private final LoginRepository loginRepository;
    private final CreatePasswordService createPasswordService;

    @Transactional
    public LoginDetaisResponse register(LoginRegisterRequest request) {

        var login = LoginMapper.toEntity(request);

        String senhaCriptografada =
                createPasswordService.passwordEncoder(request.password());

        login.setPassword(senhaCriptografada);

        UserEntity user = UserEntity.builder()
                .name(request.name())
                .dateOfBirth(request.dateOfBirth())
                .build();

        login.setUser(user);
        user.setLogins(login);

        loginRepository.save(login);

        return LoginMapper.toDetaislResponse(login);
    }
}