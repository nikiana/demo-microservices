package com.sberbank.demoProject.coursesMicroservice.mappers;

import com.sberbank.demoProject.coursesMicroservice.models.Course;
import com.sberbank.demoProject.coursesMicroservice.models.CourseRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-30T14:45:24+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17 (Homebrew)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course toEntity(CourseRequest courseRequest) {
        if ( courseRequest == null ) {
            return null;
        }

        Course course = new Course();

        course.setName( courseRequest.getName() );
        course.setDescription( courseRequest.getDescription() );
        course.setPrice( courseRequest.getPrice() );

        return course;
    }
}
