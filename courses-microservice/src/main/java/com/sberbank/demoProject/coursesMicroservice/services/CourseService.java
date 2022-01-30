package com.sberbank.demoProject.coursesMicroservice.services;

import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.CourseNotFoundException;
import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.SaveCourseException;
import com.sberbank.demoProject.coursesMicroservice.models.requests.CourseRequest;
import com.sberbank.demoProject.coursesMicroservice.models.responses.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse getCourseById(Long id) throws CourseNotFoundException;

    List<CourseResponse> getAllCourses();

    void deleteById(Long id) throws CourseNotFoundException;

    CourseResponse saveCourse(CourseRequest course) throws SaveCourseException;
}
