package com.ltc.securitydemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
//                contact = @Contact(
//                        name = "Product Management",
//                        email = "productmanagement@gmail.com",
//                        url = "https://product-managment/api"
//                ),
                description = "OpenApi documentation for Frontend",
                title = "Esprit JDMA",
                version = "1.0"
//                license = @License(
//                        name = "Licence name",
//                        url = "https://some-url.com"
//                ),
//                termsOfService = "Terms of service"
//        ),
//        servers = {
//                @Server(
//                        description = "Local ENV",
//                        url = "http://localhost:8080/property"
//                ),
//                @Server(
//                        description = "SERVER ENV",
//                        url = "http://173.212.239.87:8080/property"
//                )
//        },
//        security = {
//                @SecurityRequirement(
//                        name = "bearerAuth"
//                )
//        }

//@SecurityScheme(
//        name = "bearerAuth",
//        description = "JWT auth description",
//        scheme = "bearer",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        in = SecuritySchemeIn.HEADER
))
public class OpenApiConfig {
}
