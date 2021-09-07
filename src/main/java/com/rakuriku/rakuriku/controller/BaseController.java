package com.rakuriku.rakuriku.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.rakuriku.rakuriku.entities.AdminEntity;
import com.rakuriku.rakuriku.request.LoginRequest;
import com.rakuriku.rakuriku.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javassist.tools.web.BadHttpRequest;

@Controller
@RequestMapping("/api")
public class BaseController {
    // クレデンシャルを検証するためのサービスクラス
    private final AdminService service;
    
    // トークンを作成するためのProvider
    private final JWTProvider provider;

    @Autowired
    public BaseController(AdminService service, JWTProvider provider) {
        this.service = service;
        this.provider = provider;
    }

    @GetMapping(path = "/")
    public String getSpa() {
        return "forward:/200.html";
    }

    @GetMapping("/test")
    @ResponseBody
    public List<AdminEntity> getTest() {
        System.out.println("aaaaaa");
        return service.getAll();
    }

    // Formデータでクレデンシャルをもらい、認証を行う
    @PostMapping("/login")
    public void login(@Validated @RequestBody LoginRequest form, HttpServletResponse response) throws BadHttpRequest {
        // クレデンシャルからユーザ情報を取得
        AdminEntity user = this.service.getLoginAdminUser(form.getUserId(), form.getPassword());
        // 取得した情報でトークンを作成
        String token = this.provider.createToken(user);
        System.out.println(token);
        // Headerにトークンを作成して載せる
        response.setHeader("X-AUTH-TOKEN", this.provider.createToken(user));
        // HTTP Statusは200 OK
        response.setStatus(HttpStatus.OK.value());
    }
}
