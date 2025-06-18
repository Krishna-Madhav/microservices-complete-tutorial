package com.lcwd.user.service.exceptions;

import com.lcwd.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Centralized Exception Handler

// Any exception which occurs in any part of the program can be handled here from this class.

@RestControllerAdvice
public class GlobalExceptionHandler {

    // If any exception of type ResourceNotFoundException class is occurred then this method will be called.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exception)  {

        String message = exception.getMessage();
        ApiResponse apiResponse = ApiResponse.builder()
                .message(message)
                .success(false)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
}