package com.sberbank.demoProject.coursesMicroservice.services.impl;

import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.CourseNotFoundException;
import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.SaveCourseException;
import com.sberbank.demoProject.coursesMicroservice.mappers.CourseMapper;
import com.sberbank.demoProject.coursesMicroservice.models.Course;
import com.sberbank.demoProject.coursesMicroservice.models.CourseRequest;
import com.sberbank.demoProject.coursesMicroservice.repository.CourseRepository;
import com.sberbank.demoProject.coursesMicroservice.services.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements InitializingBean, CourseService {

    private List<Course> coursesList;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public Course getCourseById(Long id) throws CourseNotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: " + id + " not found"));
    }

    public List<Course> getAllCourses(){
        return coursesList;
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course saveCourse(CourseRequest course) throws SaveCourseException {
        try {
            return courseRepository.save(courseMapper.toEntity(course));
        }
        catch (Exception e) {
            throw new SaveCourseException("Failed to save product: " + course.toString() + ". Error: " + e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() {
        updateCourses();
    }

    @Scheduled(cron = "${cron.expression}")
    private void updateCourses() {
        coursesList = courseRepository.findAll();
        log.debug("Updated cached Courses List, " + LocalDateTime.now());
    }
}
