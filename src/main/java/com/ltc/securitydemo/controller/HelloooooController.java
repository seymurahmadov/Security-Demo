package com.ltc.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salamlasmaq")
public class HelloooooController {



    @GetMapping("/hello/nihad")
    public String sayHello() {
        return "Hello Nihad";
    }

    @GetMapping("/hello/humbet")
    public String sayHelloo() {
        return "Hello Humbet";
    }

    @GetMapping("/bye")
    public String sayBye() {
        return "Bye World";
    }



    // Inversion Of Control - prinsip
    // Dependency Injection - bu prinsipin tetbiq olunmasi ucun bir usul




    // DTO  --- Data Trasnsfer Object


}
