package com.sberbank.demoProject.adminMicroservice.services;

import com.sberbank.demoProject.adminMicroservice.exception.InvalidRoleException;
import com.sberbank.demoProject.adminMicroservice.exception.UniqueKeyViolationException;
import com.sberbank.demoProject.adminMicroservice.models.requests.UserRequest;
import com.sberbank.demoProject.adminMicroservice.models.responces.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse createUser(UserRequest userRequest) throws InvalidRoleException, UniqueKeyViolationException;

    void blockUser(Long userId, boolean enabled);
}
