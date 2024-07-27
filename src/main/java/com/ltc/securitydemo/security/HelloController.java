package com.ltc.securitydemo.security;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello";
    }
}
