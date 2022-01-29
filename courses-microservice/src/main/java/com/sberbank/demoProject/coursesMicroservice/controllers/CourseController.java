package com.sberbank.demoProject.coursesMicroservice.controllers;

import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.CourseNotFoundException;
import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.SaveCourseException;
import com.sberbank.demoProject.coursesMicroservice.models.Course;
import com.sberbank.demoProject.coursesMicroservice.models.CourseRequest;
import com.sberbank.demoProject.coursesMicroservice.services.impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
@Slf4j
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    @GetMapping
    public List<Course> getAllCourses(@RequestHeader(value = "USER_ID", defaultValue = "3") Long userId) {
        return courseServiceImpl.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) throws CourseNotFoundException {
        return courseServiceImpl.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id)  {
        courseServiceImpl.deleteById(id);
    }

    @PostMapping
    public Course saveCourse(@Validated @RequestBody CourseRequest course) throws SaveCourseException {
        return courseServiceImpl.saveCourse(course);
    }
}
