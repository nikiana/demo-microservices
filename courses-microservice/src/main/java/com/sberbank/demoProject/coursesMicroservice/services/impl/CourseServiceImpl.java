package com.sberbank.demoProject.coursesMicroservice.services.impl;

import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.CourseNotFoundException;
import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.SaveCourseException;
import com.sberbank.demoProject.coursesMicroservice.mappers.CourseMapper;
import com.sberbank.demoProject.coursesMicroservice.models.entities.Course;
import com.sberbank.demoProject.coursesMicroservice.models.requests.CourseRequest;
import com.sberbank.demoProject.coursesMicroservice.models.responses.CourseResponse;
import com.sberbank.demoProject.coursesMicroservice.repository.CourseRepository;
import com.sberbank.demoProject.coursesMicroservice.services.CourseService;
import com.sberbank.demoProject.coursesMicroservice.services.cash.CourseCash;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для бизнес-логики, связанной с курсами
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseCash courseCash;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseResponse getCourseById(Long id) throws CourseNotFoundException {
        Course course = getCourse(id);
        return courseMapper.toDTO(course);
    }

    /**
     * В этом сервисе мы не обращаемся к БД, а возвращаем закэшированный лист из сервиса CourseCash.
     * (Предполагаем, что у нас большое количество курсов, и данные редко обновляются)
     */
    public List<CourseResponse> getAllCourses() {
        return courseMapper.toDTOs(courseCash.getCashedCoursesList());
    }

    /**
     * Удаляем курс из БД и асинхронно - из кэша
     */
    public void deleteById(Long id) throws CourseNotFoundException {
        Course course = getCourse(id);
        courseRepository.delete(course);
        courseCash.deleteCourse(course);
    }

    /**
     * Сохраняем курс в БД и асинхронно вносим в закэшированный список
     */
    public CourseResponse saveCourse(CourseRequest courseRequest) throws SaveCourseException {
        try {
            Course course = courseRepository.save(courseMapper.toEntity(courseRequest));
            courseCash.addCourse(course);
            return courseMapper.toDTO(course);
        } catch (Exception e) {
            throw new SaveCourseException("Failed to save course. Error: " + e.getMessage());
        }
    }

    private Course getCourse(Long id) throws CourseNotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: " + id + " not found"));
    }
}
