package com.sberbank.demoProject.adminMicroservice.exception;

public class UniqueKeyViolationException extends Exception {

    public UniqueKeyViolationException(String message) {
        super(message);
    }
}
