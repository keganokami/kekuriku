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
}
