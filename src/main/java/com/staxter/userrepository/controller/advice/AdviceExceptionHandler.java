package com.staxter.userrepository.controller.advice;

import com.staxter.userrepository.utils.exception.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdviceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<AlreadyExistException> handleUserAlreadyExistsException() {
        AlreadyExistException exc = new AlreadyExistException("USER_ALREADY_EXISTS", "A user with the given username already exists");
        return new ResponseEntity<>(exc, HttpStatus.CONFLICT);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class AlreadyExistException {
        private String code;
        private String description;
    }
}