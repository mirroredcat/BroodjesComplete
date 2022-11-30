package be.abis.menuapi.controller;

import be.abis.menuapi.error.ApiError;
import be.abis.menuapi.exceptions.SandwichAlreadyExistsException;
import be.abis.menuapi.exceptions.SandwichNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value= SandwichAlreadyExistsException.class)
    protected ResponseEntity<? extends Object> handleSandwichAlreadyExists(SandwichAlreadyExistsException saee, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("sandwich already exists", status.value(), saee.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value= SandwichNotFoundException.class)
    protected ResponseEntity<? extends Object> handleSandwichNotFound(SandwichNotFoundException snfe, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("sandwich not found", status.value(), snfe.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }
}


