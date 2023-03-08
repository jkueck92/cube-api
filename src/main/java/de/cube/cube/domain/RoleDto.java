package de.cube.cube.domain;

import de.cube.cube.database.entity.Role;
import de.cube.cube.database.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private Long id;

    private String name;

    public static RoleDto map(Role role) {
        return RoleDto.builder().id(role.getId()).name(role.getName()).build();
    }

}
