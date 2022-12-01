package be.abis.sessionapi.controller;

import be.abis.sessionapi.error.ApiError;
import be.abis.sessionapi.exceptions.SessionNotFoundException;
import be.abis.sessionapi.model.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = SessionNotFoundException.class)
    protected ResponseEntity<? extends Object> handleSessionNotFound(SessionNotFoundException sessionNotFoundException, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError error = new ApiError("Session not found", status.value(), sessionNotFoundException.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(error, responseHeaders, status);
    }
}
