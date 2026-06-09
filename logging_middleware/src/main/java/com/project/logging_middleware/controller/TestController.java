package com.project.logging_middleware.controller;

import com.project.logging_middleware.service.LoggingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final LoggingService ls;

    public TestController(LoggingService ls) {
        this.ls = ls;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {

        String response = ls.Log(
                "backend",
                "info",
                "controller",
                "Test endpoint called successfully"
        );

        return ResponseEntity.ok(response);
    }
}

