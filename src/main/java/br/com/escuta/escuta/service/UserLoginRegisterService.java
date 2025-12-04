package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.UserLoginRegisterRequest;
import br.com.escuta.escuta.controller.response.UserLoginDetaisResponse;
import br.com.escuta.escuta.entity.UserPerfilEntity;
import br.com.escuta.escuta.mapper.UserLoginMapper;
import br.com.escuta.escuta.repository.UserLoginRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserLoginRegisterService {

    private final UserLoginRepository userLoginRepository;
    private final PasswordEncoder passwordEncoder;
    private final CreatePasswordService createPasswordService;

    @Transactional
    public UserLoginDetaisResponse register(UserLoginRegisterRequest request) {

        var userLogin = UserLoginMapper.toEntity(request);

        String senhaCriptografada = createPasswordService.passwordEncoder(request.password());

        userLogin.setPassword(senhaCriptografada);

        var perfil = UserPerfilEntity.builder()
                .profilePhoto(null)
                .description(null)
                .build();

        perfil.setUserLogin(userLogin);
        userLogin.setPerfil(perfil);

        userLoginRepository.save(userLogin);

        return UserLoginMapper.toDetaislResponse(userLogin);
    }
}