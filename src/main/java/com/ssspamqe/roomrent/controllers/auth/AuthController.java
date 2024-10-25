package com.ssspamqe.roomrent.controllers.auth;

import com.ssspamqe.roomrent.controllers.auth.request.SignInRequest;
import com.ssspamqe.roomrent.controllers.auth.request.SignUpRequest;
import com.ssspamqe.roomrent.controllers.auth.response.JwtAuthenticationResponse;
import com.ssspamqe.roomrent.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }
}
