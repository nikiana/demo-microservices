package com.sberbank.demoProject.coursesMicroservice.services.cash;

import com.sberbank.demoProject.coursesMicroservice.models.entities.Course;
import com.sberbank.demoProject.coursesMicroservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Cервис для кэширования курсов
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseCash implements InitializingBean {

    private List<Course> cashedCoursesList;
    private final CourseRepository courseRepository;

    /**
     * Заполняем кэш курсов записями из БД при поднятии приложения
     */
    @Override
    public void afterPropertiesSet() {
        updateCourses();
    }

    /**
     * Обновляем кэш по расписанию, временной интервал указан в application.properties
     */
    @Scheduled(cron = "${cron.expression}")
    public void updateCourses() {
        cashedCoursesList = courseRepository.findAll();
        log.debug("Updated cached Courses List, " + LocalDateTime.now());
    }

    /**
     * Удаляем курс из списка
     */
    @Async
    public void deleteCourse(Course course) {
        cashedCoursesList.remove(course);

    }

    /**
     * Добавляем курс в список
     */
    @Async
    public void addCourse(Course course) {
        cashedCoursesList.add(course);
    }

    /**
     * Возвращаем закэшированный список курсов
     */
    public List<Course> getCashedCoursesList() {
        return cashedCoursesList;
    }
}
