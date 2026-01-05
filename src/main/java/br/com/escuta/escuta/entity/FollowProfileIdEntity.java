package br.com.escuta.escuta.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA requires
@AllArgsConstructor
@EqualsAndHashCode
public class FollowProfileIdEntity implements Serializable {
    private Long followerId;
    private Long followedId;
}