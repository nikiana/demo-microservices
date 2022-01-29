package com.sberbank.demoProject.adminMicroservice.controllers;

import com.sberbank.demoProject.adminMicroservice.exception.InvalidRoleException;
import com.sberbank.demoProject.adminMicroservice.exception.UniqueKeyViolationException;
import com.sberbank.demoProject.adminMicroservice.models.requests.EnableUserRequest;
import com.sberbank.demoProject.adminMicroservice.models.requests.UserRequest;
import com.sberbank.demoProject.adminMicroservice.models.responces.CourseResponse;
import com.sberbank.demoProject.adminMicroservice.models.responces.UserResponse;
import com.sberbank.demoProject.adminMicroservice.models.responces.UsersAndCoursesResponse;
import com.sberbank.demoProject.adminMicroservice.services.CoursesService;
import com.sberbank.demoProject.adminMicroservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class AdminController {

    private final CoursesService coursesService;
    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest)
            throws InvalidRoleException, UniqueKeyViolationException {
        return userService.createUser(userRequest);
    }

    @PatchMapping("/{id}")
    public void blockUser(@PathVariable Long id, @RequestBody EnableUserRequest request) {
        userService.blockUser(id, request.isEnabled());
    }

    @GetMapping("/all")
    public UsersAndCoursesResponse getAllUsersAndCourses() {
        List<CourseResponse> allCourses = coursesService.getAllCourses();
        List<UserResponse> allUsers = userService.getAllUsers();
        return new UsersAndCoursesResponse(allCourses, allUsers);
    }
}
