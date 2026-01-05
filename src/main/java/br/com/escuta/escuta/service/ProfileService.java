package br.com.escuta.escuta.service;

import br.com.escuta.escuta.controller.response.ProfileResponse;
import br.com.escuta.escuta.entity.FollowProfileEntity;
import br.com.escuta.escuta.entity.FollowProfileIdEntity;
import br.com.escuta.escuta.entity.UserEntity;
import br.com.escuta.escuta.exception.BusinessException;
import br.com.escuta.escuta.mapper.ProfileMapper;
import br.com.escuta.escuta.repository.FollowProfileRepository;
import br.com.escuta.escuta.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final FollowProfileRepository followRepository;

    @Transactional(readOnly = true)
    public ProfileResponse getProfile(Long profileId) {
        UserEntity user = userRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado"));

        long followersCount = followRepository.countByIdFollowedId(profileId);
        long followingCount = followRepository.countByIdFollowerId(profileId);

        return ProfileMapper.toProfileResponse(user, followersCount, followingCount);
    }

    @Transactional
    public void follow(Long followerId, Long targetId) {
        if (followerId.equals(targetId)) {
            throw new BusinessException("Você não pode seguir a si mesmo.");
        }

        var followId = new FollowProfileIdEntity(followerId, targetId);

        if (followRepository.existsById(followId)) {
            throw new BusinessException("Você já segue este perfil.");
        }

        UserEntity follower = userRepository.findById(followerId)
                .orElseThrow(() -> new EntityNotFoundException("Seguidor não encontrado"));
        UserEntity target = userRepository.findById(targetId)
                .orElseThrow(() -> new EntityNotFoundException("Perfil alvo não encontrado"));

        FollowProfileEntity relation = FollowProfileEntity.create(follower, target);
        followRepository.save(relation);
    }
}
