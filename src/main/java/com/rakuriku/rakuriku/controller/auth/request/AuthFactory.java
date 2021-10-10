package com.rakuriku.rakuriku.controller.auth.request;

import com.rakuriku.rakuriku.entities.auth.AdminsEntity;

import org.springframework.stereotype.Component;

@Component
public class AuthFactory {
    public AdminsEntity createAdminEntityForRegister(SignUpRequest request) {
        return new AdminsEntity(request.getUserId(), request.getPassword(), request.getEmailAddress(), "admin");
    }
}
