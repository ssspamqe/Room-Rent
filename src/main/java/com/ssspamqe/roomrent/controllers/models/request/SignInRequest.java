package com.ssspamqe.roomrent.controllers.models.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}