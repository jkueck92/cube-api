package de.cube.cube.domain.dto.update;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isDisabled;

}
