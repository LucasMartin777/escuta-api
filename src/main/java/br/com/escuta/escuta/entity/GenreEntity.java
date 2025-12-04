package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "GENRE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre")
    private List<MusicEntity> musics;

    @Builder.Default
    private Boolean isActive = true;

    public void logicalExclusion(GenreEntity genreEntity) {
        genreEntity.setIsActive(false);
    }
}