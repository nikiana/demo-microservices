package com.sberbank.demoProject.authmicroservice.mapper;

import com.sberbank.demoProject.authmicroservice.models.Role;
import com.sberbank.demoProject.authmicroservice.models.User;
import com.sberbank.demoProject.authmicroservice.security.SecurityUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Маппинг классов с помощью mapstruct (автогенерирует реализацию на основе описанного интерфейса)
 * Добавляем (componentModel = "spring"), чтобы сгенерированный класс имел аннотацию @Component и автоматически подцеплялся
 */
@Mapper(componentModel = "spring")
public interface UserMapper{
    String ROLE_PREFIX = "ROLE_";

    /**
     * Маппинг entity User в объект Spring Security
     */
    @Mapping(target = "username", source = "user.email")
    @Mapping(target = "authorities", expression = "java(rolesToAuthorities(user.getRoles()))")
    SecurityUser toSecurityUser(User user);

    /**
     * Маппинг ролей (entity) в SimpleGrantedAuthority
     */
    @Mapping(target = "role", source = "role.name")
    SimpleGrantedAuthority toGrantedAuthority(Role role);

    Set<SimpleGrantedAuthority> toGrantedAuthorities(Set<Role> roles);

    /**
     * Вспомогательный метод для маппинга ролей
     */
    default Set<SimpleGrantedAuthority> rolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(ROLE_PREFIX + r.getName())).collect(Collectors.toSet());
    }

}
