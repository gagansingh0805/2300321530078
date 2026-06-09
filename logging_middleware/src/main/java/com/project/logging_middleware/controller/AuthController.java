package com.project.logging_middleware.controller;

import com.project.logging_middleware.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService as;

    @GetMapping("/token")
    public String token() {

        return as.getToken();
    }
}