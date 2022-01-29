package com.sberbank.demoProject.adminMicroservice.exception.controller;

import com.sberbank.demoProject.adminMicroservice.exception.CoursesFeignClientException;
import com.sberbank.demoProject.adminMicroservice.exception.InvalidRoleException;
import com.sberbank.demoProject.adminMicroservice.exception.NotFoundException;
import com.sberbank.demoProject.adminMicroservice.models.responces.MessageResponse;
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

/**
 * Обработка исключений на уровне контроллера.
 * @ResponseStatus - устанавливаем HTTP статус ответа
 * MessageResponse - наше кастомное тело ответа при возникшем исключении
 */
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработка исключения CoursesFeignClientException
     */
    @ExceptionHandler(CoursesFeignClientException.class)
    public MessageResponse handleCoursesException(CoursesFeignClientException e) {
        return MessageResponse.builder()
                .message(e.getMessage())
                .errorCode(e.getHttpStatus().value())
                .build();
    }

    /**
     * Обработка исключений InvalidRoleException или UniqueKeyViolationException
     */
    @ExceptionHandler({InvalidRoleException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleUserCreationException(Exception e) {
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
