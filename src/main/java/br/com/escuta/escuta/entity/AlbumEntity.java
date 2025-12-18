package br.com.escuta.escuta.entity;

import br.com.escuta.escuta.controller.request.AlbumUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Table(name = "albums")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@SQLRestriction("is_active = 1")
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Column(name = "album_duration")
//    private int albumDuration;

    private LocalDate releaseDate;

    @Column(name = "album_cover")
    private String albumCover;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "album")
    private List<MusicEntity> musics = new ArrayList<>();

    @Builder.Default
    private Boolean isActive = true;

    public void logicalExclusion() {
        this.isActive = false;
    }

    public void update(AlbumUpdateRequest request) {
        Optional.ofNullable(request.name())
                .filter(n -> !n.isBlank())
                .ifPresent(this::setName);

        Optional.ofNullable(request.albumCover())
                .filter(n -> !n.isBlank())
                .ifPresent(this::setAlbumCover);

        Optional.ofNullable(request.description())
                .filter(n -> !n.isBlank())
                .ifPresent(this::setDescription);
    }
}
