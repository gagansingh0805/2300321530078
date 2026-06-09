package com.project.logging_middleware.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/logs")
public class LogController {

    @PostMapping
    public String saveLog(
            @RequestBody Map<String, String> body
    ) {

        System.out.println(body);

        return "Log Saved";
    }
}