package com.empik.interview.exercise.controller;

import com.empik.interview.exercise.exception.UserDataProcessorException;
import com.empik.interview.exercise.service.inf.UserDataProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {

    private final UserDataProcessor userDataProcessor;

    public UserDataController(UserDataProcessor userDataProcessor) {
        this.userDataProcessor = userDataProcessor;
    }

    @GetMapping(path = "/users/{login}")
    public ResponseEntity getUserData(@PathVariable(name = "login") String login) {
        try {
            return ResponseEntity.ok(userDataProcessor.processData(login));
        } catch (UserDataProcessorException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
