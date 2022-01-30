package com.sberbank.demoProject.adminMicroservice.services;

import com.sberbank.demoProject.adminMicroservice.exception.InvalidRoleException;
import com.sberbank.demoProject.adminMicroservice.exception.NotFoundException;
import com.sberbank.demoProject.adminMicroservice.exception.SaveUserException;
import com.sberbank.demoProject.adminMicroservice.models.requests.UserRequest;
import com.sberbank.demoProject.adminMicroservice.models.responces.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse createUser(UserRequest userRequest) throws InvalidRoleException, SaveUserException;

    void blockUser(Long userId, boolean enabled) throws NotFoundException;

    void deleteUser(Long id) throws NotFoundException;
}
