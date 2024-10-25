package com.ssspamqe.roomrent.controllers.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String token;

    public static JwtAuthenticationResponse withToken(String token) {
        return new JwtAuthenticationResponse(token);
    }
}
