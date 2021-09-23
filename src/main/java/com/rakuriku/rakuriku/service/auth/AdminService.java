package com.rakuriku.rakuriku.service.auth;

import java.util.List;

import com.rakuriku.rakuriku.entities.auth.AdminsEntity;
import com.rakuriku.rakuriku.repository.auth.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javassist.tools.web.BadHttpRequest;

@Service
public class AdminService {
    private final AdminRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public AdminService(AdminRepository repo) {
        this.repo = repo;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<AdminsEntity> getAll() {
        return repo.findAll();
    }

    public AdminsEntity getLoginAdminUser(String userId, String password) throws BadHttpRequest {
        if (userId.isEmpty() || password.isEmpty()) {
            return null;
        }
        AdminsEntity admin = getAdminUser(userId);
        if (admin == null) {
            return null;
        }
        if (encoder.matches(password, admin.getPassword())) {
            return admin;
        } else {
            throw new BadHttpRequest();
        }
    }

    public AdminsEntity getAdminUser(final String userId) {
        return repo.findByUserId(userId);
    }

    public void RegisterAdmin(AdminsEntity admin) {
        repo.save(admin);
    }
}
