package com.rakuriku.rakuriku.repository.auth;

import com.rakuriku.rakuriku.entities.auth.AdminEntity;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    AdminEntity findByUserid(String userid);

    AdminEntity findByUseridAndPassword(String userId, String password);
}
