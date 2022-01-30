package com.sberbank.demoProject.adminMicroservice.controllers;

import com.sberbank.demoProject.adminMicroservice.exception.InvalidRoleException;
import com.sberbank.demoProject.adminMicroservice.exception.NotFoundException;
import com.sberbank.demoProject.adminMicroservice.exception.SaveUserException;
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

    /**
     * Бины автовайрятся благодаря @RequiredArgsConstructor (инициализируются final переменные)
     */
    private final CoursesService coursesService;
    private final UserService userService;

    /**
     * Метод для создания нового пользователя.
     * Информация о пользователе приходит в RequestBody и проходит javax валидацию.
     * Контроллер может выбросить эксепшены, которые обработаются в ControllerExceptionHandler.
     */
    @PostMapping("/user")
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest)
            throws InvalidRoleException, SaveUserException {
        return userService.createUser(userRequest);
    }

    /**
     * Метод для удаления пользователя
     */
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) throws NotFoundException {
        userService.deleteUser(id);
    }
    /**
     * Метод для блокировки/разблокировки пользователя
     */
    @PatchMapping("/user/{id}")
    public void blockUser(@PathVariable Long id, @RequestBody EnableUserRequest request) throws NotFoundException {
        userService.blockUser(id, request.isEnabled());
    }

    /**
     * Метод возвращает список всех юзеров и список всех курсов.
     * Демонстрирует внутренний запрос из одного микросервиса в другой с помощью Feign client
     */
    @GetMapping("/all")
    public UsersAndCoursesResponse getAllUsersAndCourses() {
        List<CourseResponse> allCourses = coursesService.getAllCourses();
        List<UserResponse> allUsers = userService.getAllUsers();
        return new UsersAndCoursesResponse(allCourses, allUsers);
    }

    /**
     * Метод для демонстрации, что из Auth микросервиса нам передается id пользователя в хедере, и мы можем его использовать
     */
    @GetMapping("/whoami")
    public Long getMyId(@RequestHeader(value = "USER_ID", required = false) Long userId) {
        return userId;
    }
}
