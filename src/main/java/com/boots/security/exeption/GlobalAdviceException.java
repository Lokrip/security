package com.boots.security.exeption;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class GlobalAdviceException {

    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<Map<String, String>> handleNotCreatedUser(UserNotCreatedException exeption) {
        return buildResponse(HttpStatus.BAD_REQUEST, exeption.getMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotCreatedUser(RoleNotFoundException exeption) {
        return buildResponse(HttpStatus.BAD_REQUEST, exeption.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundBook(BookNotFoundException exeption) {
        return buildResponse(HttpStatus.BAD_REQUEST, exeption.getMessage());
    }

    private ResponseEntity<Map<String, String>> buildResponse(HttpStatus status, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

}
