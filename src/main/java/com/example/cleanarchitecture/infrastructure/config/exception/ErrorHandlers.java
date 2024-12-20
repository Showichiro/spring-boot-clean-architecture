package com.example.cleanarchitecture.infrastructure.config.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cleanarchitecture.core.exception.BadRequestException;
import com.example.cleanarchitecture.core.exception.NotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorHandlers {
    @ExceptionHandler({ EntityNotFoundException.class, NotFoundException.class })
    public ResponseEntity<?> handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleError400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleError400(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();

        List<ValidationErrorData> messages = new ArrayList<>(errors.size());

        errors.forEach(error -> {
            if (messages.stream()
                    .anyMatch(validationErrorData -> Objects.equals(validationErrorData.field(), error.getField()))) {
                ValidationErrorData validationErrorData = messages.stream()
                        .filter(v -> Objects.equals(v.field(), error.getField()))
                        .findFirst().get();

                messages.remove(validationErrorData);
                List<String> errorMessages = validationErrorData.messages();
                String errorMessage = error.getDefaultMessage();
                errorMessages.add(errorMessage);
                messages.add(new ValidationErrorData(error.getField(), errorMessages));
            } else {
                messages.add(new ValidationErrorData(error));
            }
        });

        return ResponseEntity.badRequest().body(messages);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationError(ConstraintViolationException ex) {

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        List<ValidationErrorData> messages = new ArrayList<>(constraintViolations.size());

        constraintViolations.forEach(constraintViolation -> {
            if (messages.stream().anyMatch(validationErrorData -> Objects.equals(validationErrorData.field(),
                    constraintViolation.getPropertyPath().toString()))) {
                ValidationErrorData validationErrorData = messages.stream().filter(v -> Objects
                        .equals(v.field(), constraintViolation.getPropertyPath().toString()))
                        .findFirst().get();
                messages.remove(validationErrorData);

                List<String> errorMessages = validationErrorData.messages();
                errorMessages.add(constraintViolation.getMessage());
                messages.add(new ValidationErrorData(constraintViolation.getPropertyPath().toString(),
                        errorMessages));
            }

            messages.add(new ValidationErrorData(constraintViolation.getPropertyPath().toString(),
                    Collections.singletonList(constraintViolation.getMessage())));
        });

        return ResponseEntity.badRequest().body(messages);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleError500(Exception ex) {
        log.error("Error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<?> handleError500(JpaSystemException ex) {
        log.error("Error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }

    private record ValidationErrorData(String field, List<String> messages) {
        public ValidationErrorData(FieldError error) {
            this(error.getField(), new ArrayList<>(Collections.singletonList(error.getDefaultMessage())));
        }
    }
}
