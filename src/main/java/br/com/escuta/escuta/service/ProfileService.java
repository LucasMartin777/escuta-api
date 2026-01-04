package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.request.UserUpdateRequest;
import br.com.escuta.escuta.controller.response.ProfileResponse;
import br.com.escuta.escuta.entity.UserEntity;
import br.com.escuta.escuta.mapper.ProfileMapper;
import br.com.escuta.escuta.mapper.UserMapper;
import br.com.escuta.escuta.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {

    private final UserRepository repository;
    private final AuthenticationUserService authenticationUserService;


    public ProfileResponse profileResponse(Long profileId) {
        UserEntity user1 = repository.getReferenceById(profileId);
        return ProfileMapper.toUserResponse(user1);
    }
    @Transactional
    public void follow(Long loginId, Long profileId) {

        Long userId = authenticationUserService.getAuthenticatedUserId();
        UserEntity user = userRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));


        Optional.ofNullable(request.password())
                .filter(p -> !p.isBlank())
                .map(createPasswordService::passwordEncoder)
                .ifPresent(login::setPassword);

        login.getUser().update(request);

        return UserMapper.toUserSettingsResponse(login.getUser());
    }





}
