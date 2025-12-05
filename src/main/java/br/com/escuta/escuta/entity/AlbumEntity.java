package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "albums")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "album_duration")
    private int albumDuration;

    private LocalDate releaseDate;

    @Column(name = "album_cover")
    private String albumCover;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @Builder.Default
    private Boolean isActive = true;

    public void logicalExclusion(AlbumEntity albumEntity) {
        albumEntity.setIsActive(false);
    }
}
