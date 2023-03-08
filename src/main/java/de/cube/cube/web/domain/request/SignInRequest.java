package de.cube.cube.web.domain.request;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;

    private String password;

}
