package com.sberbank.demoProject.adminMicroservice.exception.controller;

import com.sberbank.demoProject.adminMicroservice.exception.CoursesFeignClientException;
import com.sberbank.demoProject.adminMicroservice.exception.InvalidRoleException;
import com.sberbank.demoProject.adminMicroservice.exception.UniqueKeyViolationException;
import com.sberbank.demoProject.adminMicroservice.models.responces.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CoursesFeignClientException.class)
    public MessageResponse handleCoursesException(CoursesFeignClientException e) {
        return MessageResponse.builder()
                .message(e.getMessage())
                .errorCode(e.getHttpStatus().value())
                .build();
    }

    @ExceptionHandler({InvalidRoleException.class, UniqueKeyViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleUserCreationException(Exception e) {
        return MessageResponse.builder()
                .message(e.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
