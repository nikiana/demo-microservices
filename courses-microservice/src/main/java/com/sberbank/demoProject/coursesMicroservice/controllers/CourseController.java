package com.sberbank.demoProject.coursesMicroservice.controllers;

import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.CourseNotFoundException;
import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.SaveCourseException;
import com.sberbank.demoProject.coursesMicroservice.models.requests.CourseRequest;
import com.sberbank.demoProject.coursesMicroservice.models.responses.CourseResponse;
import com.sberbank.demoProject.coursesMicroservice.services.impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    /**
     * Получить список всех курсов (закэшированный)
     */
    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseServiceImpl.getAllCourses();
    }

    /**
     * Получить курс по его id
     */
    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable Long id) throws CourseNotFoundException {
        return courseServiceImpl.getCourseById(id);
    }

    /**
     * Удалить курс по id
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws CourseNotFoundException {
        courseServiceImpl.deleteById(id);
    }

    /**
     * Сохранить нвый курс
     */
    @PostMapping
    public CourseResponse saveCourse(@Valid @RequestBody CourseRequest course) throws SaveCourseException {
        return courseServiceImpl.saveCourse(course);
    }
}
