package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "MUSIC")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class MusicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String audioUrl;
    private LocalDate releaseDate;
    private String singleCover;


    @ManyToOne
    @JoinColumn(name = "login_id")
    private UserLoginEntity userLogin;


    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;


    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;
}
