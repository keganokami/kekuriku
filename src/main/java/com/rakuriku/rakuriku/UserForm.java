package com.rakuriku.rakuriku;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class UserForm {
    private String userId;
    private String password;
 
    public void encrypt(PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }
 
    @Override
    public String toString() {
        return "UserForm{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
