package com.estudo.prova.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfiguration {
    @Bean
    public OpenAPI docket() {
        return new OpenAPI().info(new Info().description("Primeiro, não esqueça de criar seu usuario e fazer login com ele, na parte de usuario-controller").title("Sistema de comandas"));
    }





}
