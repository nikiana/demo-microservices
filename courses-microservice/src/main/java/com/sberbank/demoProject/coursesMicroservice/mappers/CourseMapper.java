package com.sberbank.demoProject.coursesMicroservice.mappers;

import com.sberbank.demoProject.coursesMicroservice.models.Course;
import com.sberbank.demoProject.coursesMicroservice.models.CourseRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toEntity(CourseRequest courseRequest);
}
