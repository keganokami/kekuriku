package com.rakuriku.rakuriku.controller;

import java.util.List;

import com.rakuriku.rakuriku.entities.NameEntity;
import com.rakuriku.rakuriku.service.NameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BaseController {
    @Autowired private NameService service; // ←追加

    @GetMapping(path = "/")
    public String getSpa() {
        return "forward:/index.html";
    }

    @GetMapping("/test")
    public List<NameEntity> getMethodName(@RequestParam(value = "id") String id) {
        System.out.println(id);
        return service.getAll();
    }
    
}
