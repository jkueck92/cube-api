package de.cube.cube.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role extends BaseEntity {

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

}
