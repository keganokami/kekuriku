package com.rakuriku.rakuriku.controller.test;

import java.util.List;

import com.rakuriku.rakuriku.entities.auth.AdminsEntity;
import com.rakuriku.rakuriku.service.auth.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final AdminService service;

    @Autowired
    public TestController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public List<AdminsEntity> adminEntity() {
        
       return service.getAll();
    }
    
}
