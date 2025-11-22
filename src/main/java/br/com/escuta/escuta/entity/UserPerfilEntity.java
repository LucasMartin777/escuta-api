package br.com.escuta.escuta.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    private String profilePhoto;

    private String description;

    @OneToOne
    @JoinColumn(name = "user_login_id", unique = true)
    private UserLoginEntity userLogin;


}
