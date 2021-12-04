package com.rakuriku.rakuriku.domain.service.auth;

import java.util.List;

import com.rakuriku.rakuriku.domain.model.auth.AdminsEntity;
import com.rakuriku.rakuriku.domain.repository.auth.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javassist.tools.web.BadHttpRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository repo;

    @Autowired
    private PasswordEncoder encoder;

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
        return repo.findByAdminId(userId);
    }

    public void signUpAdmin(AdminsEntity admin) {
        // TODO 登録用DTOを作って、エンティティとマッピングさせる中でエンコードするようにしたい。
        admin.setPassword(encoder.encode(admin.getPassword()));
        repo.save(admin);
    }
}
