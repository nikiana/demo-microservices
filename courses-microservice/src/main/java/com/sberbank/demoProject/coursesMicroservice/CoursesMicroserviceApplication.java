package com.sberbank.demoProject.coursesMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoursesMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesMicroserviceApplication.class, args);
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
