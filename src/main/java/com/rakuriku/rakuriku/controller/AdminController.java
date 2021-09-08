package com.rakuriku.rakuriku.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.rakuriku.rakuriku.entities.AdminEntity;
import com.rakuriku.rakuriku.request.LoginRequest;
import com.rakuriku.rakuriku.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.tools.web.BadHttpRequest;

@Controller
@RequestMapping("/api")
public class AdminController {
	private final AdminService service;
	// トークンを作成するためのProvider
	private final JWTProvider provider;

	@Autowired
	public AdminController(AdminService service, JWTProvider provider) {
		this.service = service;
		this.provider = provider;
	}

	// Formデータでクレデンシャルをもらい、認証を行う
	@PostMapping("/login")
	public void login(@Validated @RequestBody LoginRequest form, HttpServletResponse response) throws IOException {
		// クレデンシャルからユーザ情報を取得
		AdminEntity admin;
		try {
			admin = this.service.getLoginAdminUser(form.getUserId(), form.getPassword());
			
			if (admin == null) {
				response.sendError(404, "ユーザーが見つかりませんでした");
				return;
			}
			// Headerにトークンを作成して載せる
			// 取得した情報でトークンを作成
			response.setHeader("X-AUTH-TOKEN", this.provider.createToken(admin));
			response.setStatus(HttpStatus.OK.value());
		} catch (BadHttpRequest e) {
			response.sendError(500, "認証エラー" + e);
		}

	}
}
