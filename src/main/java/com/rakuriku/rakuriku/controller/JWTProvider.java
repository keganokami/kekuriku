package com.rakuriku.rakuriku.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.rakuriku.rakuriku.entities.auth.AdminsEntity;
import com.rakuriku.rakuriku.service.auth.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTProvider {

    // Signatureのエンコードに使うシークレットキー
    private static final String TOKEN_SECRET_KEY = "This is secrect!";

    // トークンの有効期間(1時間)
    private static final long TOKEN_VAILD_DURATION = 1000L * 60L * 60L;

    // ユーザ情報を取得するためのサービスクラス
    private final AdminService service;

    @Autowired
    public JWTProvider(AdminService service) {
        this.service = service;
    }

    // adminオブジェクトからJWTを作成する
    public String createToken(AdminsEntity admin) {
        Claims claims = Jwts.claims().setSubject(admin.getUserId());
        claims.put("roles", admin.getRole());

        Date iat = new Date();
        Date exp = new Date(System.currentTimeMillis() + TOKEN_VAILD_DURATION);

        return Jwts.builder().setClaims(claims).setIssuedAt(iat).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET_KEY).compact();
    }

    // トークンからユーザ情報を取得する
    public Authentication getAuthentication(final String token) {
        final AdminsEntity userDetails = this.service.getAdminUser(this.getSubject(token));
        // admin権限しか登録しないので、ユーザーが見つかれば認証成功とする
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDetails.getRole()));
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getRole(), authorities);
    }

    // トークンからユーザ名を取得する
    public String getSubject(final String token) {
        return Jwts.parser().setSigningKey(JWTProvider.TOKEN_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // リクエストのHeaderからトークンを取得する
    public String resolveToken(final HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // トークンの有効期間を検証する
    public boolean validateToken(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parser().setSigningKey(JWTProvider.TOKEN_SECRET_KEY).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
