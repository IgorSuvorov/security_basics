package com.example.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Igor Suvorov
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException {
    private String todoTitle;
    private String fieldName;
    private Long fieldValue;

    public TodoNotFoundException(String todoTitle, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : '%s'", todoTitle, fieldName, fieldValue));
        this.todoTitle = todoTitle;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
