package com.rakuriku.rakuriku.repository.auth;

import com.rakuriku.rakuriku.entities.auth.AdminsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminsEntity, Integer> {
    AdminsEntity findByUserId(String userId);

    AdminsEntity findByUserIdAndPassword(String userId, String password);
}
