package br.com.escuta.escuta.entity;

import br.com.escuta.escuta.controller.request.MusicUpdateRequest;
import br.com.escuta.escuta.controller.request.PerfilUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

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

    @Column(name = "user_name")
    private String userName;

    private String email;

    private String password;

    private String cpf;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "userLogin", cascade = CascadeType.ALL)
    private UserPerfilEntity perfil;

    @OneToMany(mappedBy = "userLogin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MusicEntity> musics = new ArrayList<>();

    @OneToMany(mappedBy = "userLogin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AlbumEntity> albums = new ArrayList<>();

    @OneToMany(mappedBy = "userLogin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlaylistEntity> playlists = new ArrayList<>();

    @Builder.Default
    private Boolean isActive = true;

    public void logicalExclusion(UserLoginEntity userLoginEntity) {
        userLoginEntity.setIsActive(false);
    }

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

    public void update(PerfilUpdateRequest request) {

        Optional.ofNullable(request.userName())
                .filter(n -> !n.isBlank())
                .ifPresent(this::setUserName);

        Optional.ofNullable(request.description())
                .filter(d -> !d.isBlank())
                .ifPresent(d -> getPerfil().setDescription(d));

        Optional.ofNullable(request.profilePhoto())
                .filter(d -> !d.isBlank())
                .ifPresent(d -> getPerfil().setProfilePhoto(d));
    }

    public void logicalExclusion() {
        this.isActive = false;
    }
}
