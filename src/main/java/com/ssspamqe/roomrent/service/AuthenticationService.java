package com.ssspamqe.roomrent.service;

import com.ssspamqe.roomrent.controllers.models.request.SignInRequest;
import com.ssspamqe.roomrent.controllers.models.request.SignUpRequest;
import com.ssspamqe.roomrent.controllers.models.response.JwtAuthenticationResponse;
import com.ssspamqe.roomrent.domain.entities.users.Role;
import com.ssspamqe.roomrent.domain.entities.users.User;
import com.ssspamqe.roomrent.security.JwtService;
import com.ssspamqe.roomrent.security.entities.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityUserService securityUserService;
    private final UserDetailsService userDetailsService;

    @Transactional
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        var user = User.builder()
                .name(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .roles(Set.of(Role.VIEWER))
                .build();
        var savedUserId = userService.create(user).getId();

        String hashedPassword = securityUserService.savePassword(savedUserId, signUpRequest.getPassword())
                .getPasswordHash();

        var token = buildJwtTokenWithUserAndPassword(user, hashedPassword);
        return JwtAuthenticationResponse.withToken(token);
    }

    private String buildJwtTokenWithUserAndPassword(User user, String hashedPassword) {
        var userDetails = SecurityUserDetails.builder()
                .user(user)
                .passwordHash(hashedPassword)
                .build();
        return jwtService.generateToken(userDetails);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword()
        );
        authenticationManager.authenticate(authToken);

        var securityUserDetails = userDetailsService.loadUserByUsername(signInRequest.getUsername());
        var jwt = jwtService.generateToken(securityUserDetails);

        return new JwtAuthenticationResponse(jwt);
    }
}
