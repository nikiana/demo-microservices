package com.sberbank.demoProject.coursesMicroservice.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация документации OpenApi (Swagger)
 *
 * Указываем в url сервера "http://localhost:8080/courses", так как все запросы с локалки должны проходить через AUTH микросервис,
 * и дальше по пути /courses переадресовываться на закрытый микросервис курсов.
 */
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080/courses"))
                .info(new Info().title("SpringShop API").version("V0")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}