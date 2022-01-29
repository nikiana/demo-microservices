package com.sberbank.demoProject.adminMicroservice.exception;


import feign.Response;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CoursesFeignClientException extends Exception {
    HttpStatus httpStatus;

    public CoursesFeignClientException(String message, Response response){
        super(message);
        this.httpStatus = HttpStatus.valueOf(response.status());
    }
}
