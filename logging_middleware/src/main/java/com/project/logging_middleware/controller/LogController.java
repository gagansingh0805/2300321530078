package com.project.logging_middleware.controller;

import com.project.logging_middleware.service.LoggingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LoggingService ls;

    public LogController(LoggingService ls) {
        this.ls = ls;
    }

    @PostMapping
    public ResponseEntity<String> saveLog(
            @RequestBody Map<String, String> body
    ) {

        String response = ls.Log(
                body.get("stack"),
                body.get("level"),
                body.get("package"),
                body.get("message")
        );

        return ResponseEntity.ok(response);
    }
}