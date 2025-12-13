package br.com.escuta.escuta.entity;

import br.com.escuta.escuta.controller.request.MusicUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Table(name = "musics")
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

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "single_cover")
    private String singleCover;

    @ManyToMany(mappedBy = "music")
    private List<PlaylistEntity> playlists;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    public void update(MusicUpdateRequest request) {

        Optional.ofNullable(request.name())
                .filter(n -> !n.isBlank())
                .ifPresent(this::setName);

        Optional.ofNullable(request.singleCover())
                .filter(n -> !n.isBlank())
                .ifPresent(this::setSingleCover);

        Optional.ofNullable(request.genreId())
                .ifPresent(genreId -> {
                    GenreEntity g = new GenreEntity();
                    g.setId(genreId);
                    this.setGenre(g);
                });
    }

    public void logicalExclusion() {
        this.isActive = false;
    }
}
