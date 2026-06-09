package com.project.vehicle_maintence_scheduler.logging;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoggingService {

    private final RestTemplate rt =
            new RestTemplate();

    private static final String URL =
            "http://localhost:8081/logs";

    public void log(
            String stack,
            String level,
            String pkg,
            String message
    ) {

        try {

            Map<String, String> body =
                    new HashMap<>();

            body.put("stack", stack);
            body.put("level", level);
            body.put("package", pkg);
            body.put("message", message);

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON
            );

            HttpEntity<Map<String, String>> entity =
                    new HttpEntity<>(
                            body,
                            headers
                    );

            rt.postForEntity(
                    URL,
                    entity,
                    String.class
            );

        } catch (Exception e) {

            System.out.println(
                    "Logging failed"
            );
        }
    }
}