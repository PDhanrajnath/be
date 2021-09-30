package com.greencommute.jobmanagementservice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.greencommute.jobmanagementservice.exception.JobManagementServiceException;
import com.greencommute.jobmanagementservice.exception.JobNotFoundException;
import com.greencommute.jobmanagementservice.model.ImmutableErrorResponse;

@RestControllerAdvice
public class ExceptionHandlerManager {

    @ExceptionHandler
    public ResponseEntity<ImmutableErrorResponse> handleBusinessException(JobManagementServiceException exception) {
        final ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .description("CONFLICT")
                .statusCode(CONFLICT.value())
                .timeStamp(Instant.now().toEpochMilli())
                .build();
        return new ResponseEntity<>(errorResponse, CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ImmutableErrorResponse> handleJobNotFoundException(JobNotFoundException exception) {
        final ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .description("Not Found")
                .statusCode(NOT_FOUND.value())
                .timeStamp(Instant.now().toEpochMilli())
                .build();
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ImmutableErrorResponse> handleServerError(Exception exception) {
        final ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
                .errorMessage("Request could not be processed due to an internal error.Please try again later.")
                .description("Internal Server Error")
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .timeStamp(Instant.now().toEpochMilli())
                .build();
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ImmutableErrorResponse> handleInvalidParameter(MethodArgumentTypeMismatchException exception) {
        final ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
                .errorMessage("Invalid input received by the server. input : "+exception.getValue())
                .description("Bad Request")
                .statusCode(BAD_REQUEST.value())
                .timeStamp(Instant.now().toEpochMilli())
                .build();
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
    
    @ExceptionHandler
    public ResponseEntity<ImmutableErrorResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException exception) {
    	final ImmutableErrorResponse errorResponse = ImmutableErrorResponse.builder()
                .errorMessage("Http method not supported. Method : "+ exception.getMethod())
                .description("Method not allowed")
                .statusCode(METHOD_NOT_ALLOWED.value())
                .timeStamp(Instant.now().toEpochMilli())
                .build();
        return new ResponseEntity<>(errorResponse, METHOD_NOT_ALLOWED);
    }

}