package com.ltc.securitydemo.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {
    private String email;

    private String password;

    private String role;


    public SignUpDto(String email, String password) {
        this.email = email;
        this.password = password;

    }

}
