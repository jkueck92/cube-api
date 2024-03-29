package de.cube.cube.domain.dto.create;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isDisabled;

}
