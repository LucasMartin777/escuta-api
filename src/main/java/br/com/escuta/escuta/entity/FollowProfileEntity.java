package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "follows_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowProfileEntity {

    @EmbeddedId
    private FollowProfileIdEntity id;

    @ManyToOne
    @MapsId("followerId")
    @JoinColumn(name = "follower_id")
    private UserEntity follower;

    @ManyToOne
    @MapsId("followingId")
    @JoinColumn(name = "following_id")
    private UserEntity following;

    private LocalDate followDate;
}
