package com.sberbank.demoProject.coursesMicroservice.exceptions.exceptions;

public class CourseNotFoundException extends Exception{
    public CourseNotFoundException(String message){
        super(message);
    }
}
