package br.com.escuta.escuta.entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "USER_PERFIL")//no banco
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class UserPerfilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_photo")
    private String profilePhoto;

    private String description;

    @OneToOne
    @JoinColumn(name = "user_login_id")
    private UserLoginEntity userLogin;


}
