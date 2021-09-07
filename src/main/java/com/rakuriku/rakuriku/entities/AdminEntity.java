package com.rakuriku.rakuriku.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "admins")
public class AdminEntity {
    @Id
    @Column(name = "userid")
    private String userid;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    // @JsonIgnore
    // // ユーザアカウントが満了されているかの設定
    // private boolean accountNonExpired;
    // @JsonIgnore
    // // ユーザアカウントがロックされているかの設定
    // private boolean accountNonLocked;
    // @JsonIgnore
    // // ユーザアカウントのクレデンシャルが満了されているかの設定
    // private boolean credentialsNonExpired;
    // @JsonIgnore
    // ユーザアカウントが活性化されているかの設定
    // private boolean enabled;
    // @JsonIgnore
    // 認可のためのユーザのロール

    // // @ElementCollection(fetch = FetchType.EAGER)
    // private List<String> role = new ArrayList<>();
    // @JsonIgnore
    // // ユーザの認可情報を取得する
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // return
    // this.role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableList());
    // }
}
