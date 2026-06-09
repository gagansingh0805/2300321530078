package com.project.logging_middleware.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoggingClient {

    private final RestTemplate rt;

    private static final String LOG_URL =
            "http://localhost:8081/log";

    public void log(
            String stack,
            String level,
            String pkg,
            String message
    ) {

        try {

            Map<String, String> b =
                    new HashMap<>();

            b.put("stack", stack);
            b.put("level", level);
            b.put("package", pkg);
            b.put("message", message);

            HttpHeaders h =
                    new HttpHeaders();

            h.setContentType(
                    MediaType.APPLICATION_JSON
            );

            HttpEntity<Map<String, String>> e =
                    new HttpEntity<>(b, h);

            rt.postForEntity(
                    LOG_URL,
                    e,
                    String.class
            );

        } catch (Exception ex) {

            System.out.println(
                    "Logging failed"
            );
        }
    }
}
