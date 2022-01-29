package com.sberbank.demoProject.adminMicroservice.services.implementations;

import com.sberbank.demoProject.adminMicroservice.enums.RoleEnum;
import com.sberbank.demoProject.adminMicroservice.exception.InvalidRoleException;
import com.sberbank.demoProject.adminMicroservice.exception.UniqueKeyViolationException;
import com.sberbank.demoProject.adminMicroservice.mappers.UserMapper;
import com.sberbank.demoProject.adminMicroservice.models.entities.Role;
import com.sberbank.demoProject.adminMicroservice.models.entities.User;
import com.sberbank.demoProject.adminMicroservice.models.requests.UserRequest;
import com.sberbank.demoProject.adminMicroservice.models.responces.UserResponse;
import com.sberbank.demoProject.adminMicroservice.repository.RoleRepository;
import com.sberbank.demoProject.adminMicroservice.repository.UserRepository;
import com.sberbank.demoProject.adminMicroservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private static final String UNIQUE_KEY_MESSAGE = "Юзер с таким емейлом уже существует";
    private static final String INVALID_ROLE_MESSAGE = "Список ролей юзера невалиден";
    private static final String EMPTY_ROLE_MESSAGE = "Список ролей юзера не может быть пустым";

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toDtos(userRepository.findAll());
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) throws InvalidRoleException, UniqueKeyViolationException {
        Set<Role> userRoles = getUserRoles(userRequest.getRoleEnums());
        User user = userMapper.toEntity(userRequest, userRoles);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueKeyViolationException(UNIQUE_KEY_MESSAGE);
        }
        return userMapper.toDto(user);
    }

    private Set<Role> getUserRoles(Set<RoleEnum> requestedRoles) throws InvalidRoleException {
        if (CollectionUtils.isEmpty(requestedRoles))
            throw new InvalidRoleException(EMPTY_ROLE_MESSAGE);

        Set<Role> userRoles = roleRepository.findByNameIn(requestedRoles.stream().map(Enum::name).collect(Collectors.toSet()));
        if (CollectionUtils.isEmpty(userRoles))
            throw new InvalidRoleException(INVALID_ROLE_MESSAGE);

        return userRoles;
    }

    @Override
    @Transactional
    public void blockUser(Long userId, boolean enabled) {
        userRepository.blockUser(userId, enabled);
    }
}
