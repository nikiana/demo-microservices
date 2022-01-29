package com.sberbank.demoProject.adminMicroservice.models.responces;

import lombok.Value;

import java.util.List;

@Value
public class UsersAndCoursesResponse {
    List<CourseResponse> courses;
    List<UserResponse> users;
}
