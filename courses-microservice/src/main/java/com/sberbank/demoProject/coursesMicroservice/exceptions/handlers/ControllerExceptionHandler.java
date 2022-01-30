package com.sberbank.demoProject.coursesMicroservice.exceptions.handlers;

import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.CourseNotFoundException;
import com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions.SaveCourseException;
import com.sberbank.demoProject.coursesMicroservice.models.responses.MessageResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработка исключений CourseNotFoundException и SaveCourseException
     */
    @ExceptionHandler({CourseNotFoundException.class, SaveCourseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleCoursesException(Exception e) {
        return MessageResponse.builder()
                .message(e.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    /**
     * Обработка исключений, возникших при валидации тела запроса
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        MessageResponse messageResponse = MessageResponse.builder()
                .message(ex.getLocalizedMessage())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errors(errorList)
                .build();
        return handleExceptionInternal(ex, messageResponse, headers, HttpStatus.BAD_REQUEST, request);
    }
}
