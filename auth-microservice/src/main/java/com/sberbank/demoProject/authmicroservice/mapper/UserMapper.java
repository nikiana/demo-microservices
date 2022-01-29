package com.sberbank.demoProject.authmicroservice.mapper;

import com.sberbank.demoProject.authmicroservice.models.Role;
import com.sberbank.demoProject.authmicroservice.models.User;
import com.sberbank.demoProject.authmicroservice.security.SecurityUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {SimpleGrantedAuthority.class, Set.class})
public interface UserMapper{
    String ROLE_PREFIX = "ROLE_";

    @Mapping(target = "username", source = "user.email")
    @Mapping(target = "authorities", expression = "java(rolesToAuthorities(user.getRoles()))")
    SecurityUser toSecurityUser(User user);

    @Mapping(target = "role", source = "role.name")
    SimpleGrantedAuthority toGrantedAuthority(Role role);

    Set<SimpleGrantedAuthority> toGrantedAuthorities(Set<Role> roles);

    default Set<SimpleGrantedAuthority> rolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(ROLE_PREFIX + r.getName())).collect(Collectors.toSet());
    }

}
