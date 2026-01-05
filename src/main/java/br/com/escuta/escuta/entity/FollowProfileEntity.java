package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "follows_profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FollowProfileEntity {

    @EmbeddedId
    private FollowProfileIdEntity id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("followerId")
    @JoinColumn(name = "follower_id")
    private UserEntity follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("followedId")
    @JoinColumn(name = "following_id")
    private UserEntity followed;

    @CreationTimestamp
    @Column(name = "follow_date", nullable = false, updatable = false)
    private LocalDate followedAt;

    public static FollowProfileEntity create(UserEntity follower, UserEntity followed) {
        var compositeId = new FollowProfileIdEntity(follower.getId(), followed.getId());
        return FollowProfileEntity.builder()
                .id(compositeId)
                .follower(follower)
                .followed(followed)
                .build();
    }
}