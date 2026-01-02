package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "albums")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA only
@EqualsAndHashCode(of = "id")
@SQLRestriction("is_active = 1")
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate releaseDate;

    @Column(name = "album_cover")
    private String albumCover;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "album")
    private List<MusicEntity> musics = new ArrayList<>();

    private Boolean isActive;

    @Builder
    private AlbumEntity(String name,
                        LocalDate releaseDate,
                        String albumCover,
                        String description,
                        UserEntity user) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Album name cannot be empty");
        }

        if (user == null) {
            throw new IllegalArgumentException("Album must have a user");
        }

        this.name = name;
        this.releaseDate = releaseDate;
        this.albumCover = albumCover;
        this.description = description;
        this.user = user;
        this.isActive = true;
    }

    public static AlbumEntity create(String name,
                                     LocalDate releaseDate,
                                     String albumCover,
                                     String description,
                                     UserEntity user) {
        return AlbumEntity.builder()
                .name(name)
                .releaseDate(releaseDate)
                .albumCover(albumCover)
                .description(description)
                .user(user)
                .build();
    }

    public void logicalExclusion() {
        this.isActive = false;
    }

    public void update(String name,
                       String albumCover,
                       String description) {

        if (name != null && !name.isBlank()) {
            this.name = name;
        }

        if (albumCover != null && !albumCover.isBlank()) {
            this.albumCover = albumCover;
        }

        if (description != null && !description.isBlank()) {
            this.description = description;
        }
    }
}

