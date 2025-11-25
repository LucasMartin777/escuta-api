package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "PLAYLIST")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cover;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "login_id")
    private UserLoginEntity userLogin;

    @ManyToMany
    @JoinTable(
            name = "PLAYLIST_MUSIC",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private List<MusicEntity> musics;
}
