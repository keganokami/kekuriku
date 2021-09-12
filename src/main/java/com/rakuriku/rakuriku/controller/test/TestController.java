package com.rakuriku.rakuriku.controller.test;

import java.util.List;

import com.rakuriku.rakuriku.entities.auth.AdminEntity;
import com.rakuriku.rakuriku.service.auth.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final AdminService service;

    @Autowired
    public TestController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public List<AdminEntity> adminEntity() {
        
       return service.getAll();
    }
    
}
