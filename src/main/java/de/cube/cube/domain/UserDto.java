package de.cube.cube.domain;

import de.cube.cube.database.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isDisabled;

    public static UserDto map(User user) {
        return UserDto.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).password(user.getPassword()).isDisabled(user.isDisabled()).build();
    }

}
