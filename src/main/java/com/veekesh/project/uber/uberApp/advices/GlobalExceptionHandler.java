package com.veekesh.project.uber.uberApp.advices;

import com.veekesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.veekesh.project.uber.uberApp.exceptions.RuntimeConflictException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeConflict(RuntimeConflictException exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.CONFLICT)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(errors.toString())
                .build();
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
