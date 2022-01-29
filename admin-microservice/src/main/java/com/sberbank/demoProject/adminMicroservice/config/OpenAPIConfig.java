package com.sberbank.demoProject.adminMicroservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация документации OpenApi (Swagger)
 *
 * Указываем в url сервера "http://localhost:8080/admin", так как все запросы с локалки должны проходить через AUTH микросервис,
 * и дальше по пути /admin переадресовываться на закрытый микросервис админа.
 */
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080/admin"))
                .info(new Info().title("Admin microservice API").version("V0")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}