package com.project.logging_middleware.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoggingService {

    @Value("${logging.url}")
    private String url;

    @Value("${logging.token}")
    private String token;

    public void Log(
            String stack,
            String level,
            String packageName,
            String message
    ) {

        try {

            RestTemplate rt = new RestTemplate();

            HttpHeaders h = new HttpHeaders();

            h.setContentType(MediaType.APPLICATION_JSON);

            h.setBearerAuth(token);

            Map<String, String> body = new HashMap<>();

            body.put("stack", stack);
            body.put("level", level);
            body.put("package", packageName);
            body.put("message", message);

            HttpEntity<Map<String, String>> e =
                    new HttpEntity<>(body, h);

            ResponseEntity<String> r =
                    rt.exchange(
                            url,
                            HttpMethod.POST,
                            e,
                            String.class
                    );

            System.out.println(r.getBody());

        } catch (Exception ex) {

            System.out.println(
                    "Logging failed: " + ex.getMessage()
            );
        }
    }
}

