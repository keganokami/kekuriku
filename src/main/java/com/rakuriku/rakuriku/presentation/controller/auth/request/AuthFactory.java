package com.rakuriku.rakuriku.presentation.controller.auth.request;

import com.rakuriku.rakuriku.domain.model.auth.AdminsEntity;

import org.springframework.stereotype.Component;

@Component
public class AuthFactory {
    public AdminsEntity createAdminEntityForRegister(SignUpRequest request) {
        return new AdminsEntity(request.getAdminId(), request.getPassword(), request.getEmailAddress(), "admin");
    }
}
