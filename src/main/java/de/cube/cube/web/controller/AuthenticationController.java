package de.cube.cube.web.controller;


import de.cube.cube.web.domain.request.SignInRequest;
import de.cube.cube.web.domain.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/signIn")
    public void signIn(@RequestBody SignInRequest signInRequest) {

    }

    @PostMapping("/singUp")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {

    }

}
