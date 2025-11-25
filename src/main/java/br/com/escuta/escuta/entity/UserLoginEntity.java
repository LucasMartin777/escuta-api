package br.com.escuta.escuta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "USER_LOGIN")//no banco
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class UserLoginEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    private String password;

    private String cpf;

    private Date dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "userLogin", cascade = CascadeType.ALL)
    private UserPerfilEntity perfil;

    @OneToMany(mappedBy = "userLogin", cascade = CascadeType.ALL)
    private List<MusicEntity> musics;


    @OneToMany(mappedBy = "userLogin", cascade = CascadeType.ALL)
    private List<AlbumEntity> albums;

    @OneToMany(mappedBy = "userLogin", cascade = CascadeType.ALL)
    private List<PlaylistEntity> playlists;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
