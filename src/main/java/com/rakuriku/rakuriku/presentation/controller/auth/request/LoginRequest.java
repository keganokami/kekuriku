package com.rakuriku.rakuriku.presentation.controller.auth.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String adminId;
    private String password;
}
