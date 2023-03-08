package de.cube.cube.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isDisabled = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

}
