package com.sberbank.demoProject.authmicroservice.security;

import com.sberbank.demoProject.authmicroservice.mapper.UserMapper;
import com.sberbank.demoProject.authmicroservice.models.Role;
import com.sberbank.demoProject.authmicroservice.models.User;
import com.sberbank.demoProject.authmicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * UserDetailsService используется, чтобы создать UserDetails объект путем реализации метода loadUserByUsername
 * В нашем случае уникальным логином пользователя служит его е-мейл
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        return userMapper.toSecurityUser(user);
    }
}
