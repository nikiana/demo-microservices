package com.sberbank.demoProject.authmicroservice.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * UserDetails - класс-адаптер между нашим entity User и тем, что требуется Spring Security внутри SecurityContextHolder.
 * В SimpleGrantedAuthority ко всем ролям добавляется префикс ROLE_ (см. rolesToAuthorities в UserMapper)
 */
@Data
@NoArgsConstructor
public class SecurityUser implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> authorities;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() { return id;}
}
