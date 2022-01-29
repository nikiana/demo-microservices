package com.sberbank.demoProject.adminMicroservice.services;

import com.sberbank.demoProject.adminMicroservice.models.responces.CourseResponse;

import java.util.List;

public interface CoursesService {
    List<CourseResponse> getAllCourses();
}
