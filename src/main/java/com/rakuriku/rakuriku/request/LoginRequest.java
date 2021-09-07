package com.rakuriku.rakuriku.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userId;
    private String password;
}
