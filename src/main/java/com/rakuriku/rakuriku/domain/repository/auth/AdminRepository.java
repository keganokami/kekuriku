package com.rakuriku.rakuriku.domain.repository.auth;

import com.rakuriku.rakuriku.domain.model.auth.AdminsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminsEntity, Integer> {
    AdminsEntity findByAdminId(String userId);

    AdminsEntity findByAdminIdAndPassword(String userId, String password);
}
