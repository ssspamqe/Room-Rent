package com.ssspamqe.roomrent.controllers.models.response;

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
