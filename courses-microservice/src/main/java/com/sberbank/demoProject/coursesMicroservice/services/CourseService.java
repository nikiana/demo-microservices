package com.sberbank.demoProject.coursesMicroservice.services;

import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.CourseNotFoundException;
import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.SaveCourseException;
import com.sberbank.demoProject.coursesMicroservice.models.Course;
import com.sberbank.demoProject.coursesMicroservice.models.CourseRequest;

import java.util.List;

public interface CourseService {

    Course getCourseById(Long id) throws CourseNotFoundException;

    List<Course> getAllCourses();

    void deleteById(Long id);

    Course saveCourse(CourseRequest course) throws SaveCourseException;
}
