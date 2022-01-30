package com.sberbank.demoProject.authmicroservice.config;
import com.sberbank.demoProject.authmicroservice.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Настраиваем ограничение доступа в зависимости от ролей юзера по разным контекстам
     * Включаем Http Basic Authentication
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole(RoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/courses/**").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.USER.name())
                .antMatchers("/courses/**").hasAnyRole(RoleEnum.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
