package com.rakuriku.rakuriku.app.service;

import com.rakuriku.rakuriku.domain.model.auth.AdminsEntity;
import com.rakuriku.rakuriku.presentation.controller.auth.request.LoginRequest;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@AllArgsConstructor
public final class LoginAdminDetail {
    private final AdminsEntity appUser;
    private final Collection<? extends GrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getLoginUser() {
        return appUser.getAdminId();
    }

}
