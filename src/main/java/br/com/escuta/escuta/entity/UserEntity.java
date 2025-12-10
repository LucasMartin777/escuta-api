package br.com.escuta.escuta.entity;


import br.com.escuta.escuta.controller.request.UserUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    @Column(name = "profile_photo")
    private String profilePhoto;

    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToOne
    @JoinColumn(name = "login_id", referencedColumnName = "login_id")
    private LoginEntity login;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MusicEntity> musics = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AlbumEntity> albums = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlaylistEntity> playlists = new ArrayList<>();

    @Builder.Default
    private Boolean isActive = true;

    public void update(UserUpdateRequest request) {

        Optional.ofNullable(request.name())
                .filter(n -> !n.isBlank())
                .ifPresent(this::setName);

        Optional.ofNullable(request.description())
                .filter(d -> !d.isBlank())
                .ifPresent(this::setDescription);

        Optional.ofNullable(request.profilePhoto())
                .filter(p -> !p.isBlank())
                .ifPresent(this::setProfilePhoto);
    }

    public void logicalExclusion(UserEntity userEntity) {
        userEntity.setIsActive(false);
    }
}
