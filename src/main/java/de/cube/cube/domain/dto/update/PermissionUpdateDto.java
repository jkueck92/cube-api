package de.cube.cube.domain.dto.update;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionUpdateDto {

    private Long id;

    private String name;

}
