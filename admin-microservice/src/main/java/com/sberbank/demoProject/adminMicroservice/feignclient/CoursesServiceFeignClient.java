package com.sberbank.demoProject.adminMicroservice.feignclient;

import com.sberbank.demoProject.adminMicroservice.models.responces.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${courses.service.name}", url = "${courses.service.url}")
public interface CoursesServiceFeignClient {

    @GetMapping("/demo")
    List<CourseResponse> getAllCourses();
}
