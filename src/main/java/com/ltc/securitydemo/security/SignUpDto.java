package com.ltc.securitydemo.security;

import com.ltc.securitydemo.enumaration.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {
    private String email;

    private String password;

    private RoleEnum role;


    public SignUpDto(String email, String password) {
        this.email = email;
        this.password = password;

    }

}
