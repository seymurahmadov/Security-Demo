package com.ltc.securitydemo.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AfterSignInResponseDto {
    private String token;

    private String email;

    private String password;

    private String role;

    @Override
    public String toString() {
        return "token = " + token + '\n' +
                "email = " + email + '\n' +
                "password = " + password + '\n' +
                "role = " + role;

    }
}
