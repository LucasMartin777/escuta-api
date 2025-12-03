package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.List;

@Table(name = "MUSIC")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@SQLRestriction("is_active = 1")
public class MusicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "audio_url")
    private String audioUrl;

    private LocalDate releaseDate;

    @Column(name = "single_cover")
    private String singleCover;

    @ManyToMany(mappedBy = "musics")
    private List<PlaylistEntity> playlists;


    @ManyToOne
    @JoinColumn(name = "login_id")
    private UserLoginEntity userLogin;


    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;


    public void logicalExclusion() {
        this.isActive = false;
    }
}

