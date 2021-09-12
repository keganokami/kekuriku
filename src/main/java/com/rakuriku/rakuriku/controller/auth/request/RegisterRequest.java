package com.rakuriku.rakuriku.controller.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String userId;
    private String password;
    private String emailAddress;
}
