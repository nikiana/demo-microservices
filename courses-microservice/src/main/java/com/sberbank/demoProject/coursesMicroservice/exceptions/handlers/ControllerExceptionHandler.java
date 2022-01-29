package com.sberbank.demoProject.coursesMicroservice.exceptions.handlers;

import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.CourseNotFoundException;
import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.SaveCourseException;
import com.sberbank.demoProject.coursesMicroservice.models.MessageResponse;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({CourseNotFoundException.class, SaveCourseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleCoursesException(Exception e) {
        return MessageResponse.builder()
                .message(e.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlerConstraintViolationException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream().collect(Collectors.toMap(this::keyMapper, ConstraintViolation::getMessage));
    }

    private String keyMapper(ConstraintViolation<?> constraintViolation) {
        return ((PathImpl)constraintViolation.getPropertyPath()).getLeafNode().getName();
    }

}
