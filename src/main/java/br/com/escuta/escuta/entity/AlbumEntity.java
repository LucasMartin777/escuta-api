package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "ALBUM")
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

    @Column(name = "album_cover")
    private String albumCover;

    private String description;

    @ManyToOne
    @JoinColumn(name = "login_id")
    private UserLoginEntity userLogin;

}
