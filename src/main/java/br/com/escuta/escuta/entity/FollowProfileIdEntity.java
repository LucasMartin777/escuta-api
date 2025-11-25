package br.com.escuta.escuta.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowProfileIdEntity implements Serializable {

    private Long followerId;
    private Long followingId;
}
