package be.abis.sessionapi.controller;


import be.abis.sessionapi.error.ApiError;
import be.abis.sessionapi.exceptions.SessionNotFoundException;
import be.abis.sessionapi.model.Session;
import be.abis.sessionapi.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class SessionController {

    @Autowired
    SessionService sessionService;

    @GetMapping(path ="/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> getTodaysSessions() {

        try {
            List<Session> sessionList = sessionService.getTodaysSessions();
            return new ResponseEntity<>(sessionList, HttpStatus.OK);
        } catch (SessionNotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ApiError error = new ApiError("Session not found", status.value(), e.getMessage());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
            return new ResponseEntity<ApiError>(error, responseHeaders, status);
        }
    }

}
