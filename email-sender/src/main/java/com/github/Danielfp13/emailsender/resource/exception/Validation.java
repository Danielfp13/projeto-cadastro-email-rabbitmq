package com.github.Danielfp13.emailsender.resource.exception;

import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Validation extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public Validation(LocalDateTime timestamp, String error, Integer status, String path) {
        super(timestamp, error, status, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void setErrors(FieldMessage error) {
        this.errors.add(error);
    }
}
