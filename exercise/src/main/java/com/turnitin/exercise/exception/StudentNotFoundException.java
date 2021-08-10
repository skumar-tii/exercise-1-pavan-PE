package com.turnitin.exercise.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super();
    }
    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public StudentNotFoundException(String message) {
        super(message);
    }
    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
    public StudentNotFoundException(UUID id) {
    	super(String.format("Student with Id %s not found", id.toString()));
    }
}
