package com.rakuriku.rakuriku.service;

import java.util.List;

import com.rakuriku.rakuriku.entities.AdminEntity;
import com.rakuriku.rakuriku.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javassist.tools.web.BadHttpRequest;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<AdminEntity> getAll() {
        return repo.findAll();
    }

    public AdminEntity getLoginAdminUser(String userId, String password) throws BadHttpRequest {
        if (userId.isEmpty() || password.isEmpty()) {
            return null;
        }
        AdminEntity admin = getAdminUser(userId);
        if (admin == null) {
            return null;
        }
        if (encoder.matches(password, admin.getPassword())) {
            return admin;
        } else {
            throw new BadHttpRequest();
        }
    }

    public AdminEntity getAdminUser(final String userId) {
        return repo.findByUserid(userId);
    }
}
