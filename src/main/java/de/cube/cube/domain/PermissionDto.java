package de.cube.cube.domain;

import de.cube.cube.database.entity.Permission;
import de.cube.cube.database.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionDto {

    private Long id;

    private String name;

    public static PermissionDto map(Permission permission) {
        return PermissionDto.builder().id(permission.getId()).name(permission.getName()).build();
    }

}
