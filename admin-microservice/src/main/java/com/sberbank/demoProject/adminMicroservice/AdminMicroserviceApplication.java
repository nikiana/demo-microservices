package com.sberbank.demoProject.adminMicroservice;

import com.sberbank.demoProject.adminMicroservice.feignclient.CoursesServiceFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableFeignClients(clients = CoursesServiceFeignClient.class)
public class AdminMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminMicroserviceApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer CORSConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedOrigins("*")
//						.allowedHeaders("*")
//						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
//						.maxAge(-1)   // add maxAge
//						.allowCredentials(false);
//			}
//		};
//	}
}
