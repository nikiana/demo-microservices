package com.sberbank.demoProject.adminMicroservice.feignclient;

import com.sberbank.demoProject.adminMicroservice.models.responces.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Декларативный HTTP-клиент, с помощью которого мы отправляем запросы на микросервис courses.
 */
@FeignClient(name = "${courses.service.name}", url = "${courses.service.url}")
public interface CoursesServiceFeignClient {

    /**
     * Описание сигнатуры контроллера в микросервисе courses, куда мы хотим отправить запрос
     */
    @GetMapping("/demo")
    List<CourseResponse> getAllCourses();
}
