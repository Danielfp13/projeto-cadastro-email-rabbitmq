package com.github.Danielfp13.emailsender.resource.exception;

import com.github.Danielfp13.emailsender.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResourceHadlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Validation> Method(MethodArgumentNotValidException e, HttpServletRequest request) {
        Validation error = new Validation(LocalDateTime.now(), "Erro de validação", HttpStatus.UNPROCESSABLE_ENTITY.value(),
                request.getRequestURI());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            error.setErrors(new FieldMessage(x.getField(), x.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler({ObjectNotFoundException.class, DataIntegrityViolationException.class, IllegalArgumentException.class})
    public ResponseEntity<StandardError> handleNotFoundOrIntegrity(Exception ex, HttpServletRequest request) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<StandardError> buildResponseEntity(HttpStatus status, String message, String requestURI) {
        return ResponseEntity.status(status).body(new StandardError(LocalDateTime.now(), message, status.value(), requestURI));
    }

}
