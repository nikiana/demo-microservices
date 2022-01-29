package com.sberbank.demoProject.adminMicroservice.services.implementations;

import com.sberbank.demoProject.adminMicroservice.feignclient.CoursesServiceFeignClient;
import com.sberbank.demoProject.adminMicroservice.models.responces.CourseResponse;
import com.sberbank.demoProject.adminMicroservice.services.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CoursesServiceFeignClient coursesServiceFeignClient;

    public List<CourseResponse> getAllCourses() {
        return coursesServiceFeignClient.getAllCourses();
    }
}
