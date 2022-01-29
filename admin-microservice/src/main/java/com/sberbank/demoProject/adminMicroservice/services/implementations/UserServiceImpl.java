package com.sberbank.demoProject.adminMicroservice.services.implementations;

import com.sberbank.demoProject.adminMicroservice.enums.RoleEnum;
import com.sberbank.demoProject.adminMicroservice.exception.InvalidRoleException;
import com.sberbank.demoProject.adminMicroservice.exception.NotFoundException;
import com.sberbank.demoProject.adminMicroservice.mappers.UserMapper;
import com.sberbank.demoProject.adminMicroservice.models.entities.Role;
import com.sberbank.demoProject.adminMicroservice.models.entities.User;
import com.sberbank.demoProject.adminMicroservice.models.requests.UserRequest;
import com.sberbank.demoProject.adminMicroservice.models.responces.UserResponse;
import com.sberbank.demoProject.adminMicroservice.repository.RoleRepository;
import com.sberbank.demoProject.adminMicroservice.repository.UserRepository;
import com.sberbank.demoProject.adminMicroservice.services.UserService;
import lombok.RequiredArgsConstructor;
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
    private static final String INVALID_ROLE_MESSAGE = "Список ролей юзера невалиден";

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toDtos(userRepository.findAll());
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) throws InvalidRoleException {
        Set<Role> userRoles = getUserRoles(userRequest.getRoleEnums());
        User user = userMapper.toEntity(userRequest, userRoles);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    /**
     * Маппим роли из enum на роли, которые существуют в БД. Возвращаем список ролей-сущностей, которые мы должны задать юзеру.
     */
    private Set<Role> getUserRoles(Set<RoleEnum> requestedRoles) throws InvalidRoleException {
        Set<Role> userRoles = roleRepository.findByNameIn(requestedRoles.stream().map(Enum::name).collect(Collectors.toSet()));
        if (CollectionUtils.isEmpty(userRoles))
            throw new InvalidRoleException(INVALID_ROLE_MESSAGE);
        return userRoles;
    }

    @Override
    @Transactional
    public void blockUser(Long userId, boolean enabled) throws NotFoundException {
        User user = getUser(userId);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) throws NotFoundException {
        User user = getUser(userId);
        userRepository.delete(user);
    }

    private User getUser(Long userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));
    }
}
