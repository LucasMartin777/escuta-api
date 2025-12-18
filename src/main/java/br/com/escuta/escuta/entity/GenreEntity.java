package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@SQLRestriction("is_active = 1")
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