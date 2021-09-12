package com.rakuriku.rakuriku.controller.auth.request;

import com.rakuriku.rakuriku.entities.auth.AdminEntity;

import org.springframework.stereotype.Component;

@Component
public class AuthFactory {
    public AdminEntity createAdminEntityForRegister(RegisterRequest request) {
        return new AdminEntity(request.getUserId(), request.getPassword(), request.getEmailAddress(), "admin");
    }
}
