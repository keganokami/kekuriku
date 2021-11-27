package com.rakuriku.rakuriku.presentation.controller.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.rakuriku.rakuriku.domain.model.auth.AdminsEntity;
import com.rakuriku.rakuriku.domain.service.auth.AdminService;
import com.rakuriku.rakuriku.infra.JWTProvider;
import com.rakuriku.rakuriku.presentation.controller.auth.request.AuthFactory;
import com.rakuriku.rakuriku.presentation.controller.auth.request.LoginRequest;
import com.rakuriku.rakuriku.presentation.controller.auth.request.SignUpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.tools.web.BadHttpRequest;

@Controller
@RequestMapping("/management-account")
public class AdminController {
	private final AdminService service;
	// トークンを作成するためのProvider
	private final JWTProvider provider;

	private final AuthFactory authFactory;

	@Autowired
	public AdminController(AdminService service, JWTProvider provider ,AuthFactory authFactory) {
		this.service = service;
		this.provider = provider;
		this.authFactory = authFactory;
	}

	@Transactional
	@PostMapping("/new")
	public void postMethodName(@RequestBody SignUpRequest request, HttpServletResponse response) {
		AdminsEntity newAdmin = authFactory.createAdminEntityForRegister(request);
		service.signUpAdmin(newAdmin);
		response.setHeader("X-AUTH-TOKEN", this.provider.createToken(newAdmin));
		response.setStatus(HttpStatus.OK.value());
	}
	
	@PostMapping("/login")
	public void login(@Validated @RequestBody LoginRequest form, HttpServletResponse response) throws IOException {
		// TODO サービスでUserNotFoundExceptionを作成してエラーをレスポンスするように修正する
		// TODO try catchを消したい。
		AdminsEntity admin;
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
			response.sendError(404, "認証エラー" + e);
		}

	}
}
