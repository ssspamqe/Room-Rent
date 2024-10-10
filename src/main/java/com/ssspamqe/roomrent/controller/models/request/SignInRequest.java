package com.ssspamqe.roomrent.controller.models.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
