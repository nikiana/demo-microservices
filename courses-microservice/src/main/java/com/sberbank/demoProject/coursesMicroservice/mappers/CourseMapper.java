package com.sberbank.demoProject.coursesMicroservice.mappers;

import com.sberbank.demoProject.coursesMicroservice.models.entities.Course;
import com.sberbank.demoProject.coursesMicroservice.models.requests.CourseRequest;
import com.sberbank.demoProject.coursesMicroservice.models.responses.CourseResponse;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппинг ДТО классов в entity и обратно с помощью mapstruct (автогенерирует реализацию на основе описанного интерфейса)
 * Добавляем (componentModel = "spring"), чтобы сгенерированный класс имел аннотацию @Component и автоматически подцеплялся
 */
@Mapper(componentModel = "spring")
public interface CourseMapper {

    /**
     * Маппинг из CourseRequest в entity Course
     */
    Course toEntity(CourseRequest courseRequest);

    /**
     * Маппинг из entity Course в CourseResponse
     */
    CourseResponse toDTO(Course course);

    /**
     * Отдельно прописывается маппинг для коллекций сущностей
     */
    List<CourseResponse> toDTOs(List<Course> courses);
}
