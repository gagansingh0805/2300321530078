package com.project.logging_middleware.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoggingService {

    @Value("${logging.url}")
    private String url;

    private final AuthService as;

    public LoggingService(AuthService as) {
        this.as = as;
    }

    public String Log(
            String stack,
            String level,
            String packageName,
            String message
    ) {

        try {

            String token = as.getToken();

            System.out.println("TOKEN:");
            System.out.println(token);

            SimpleClientHttpRequestFactory f =
                    new SimpleClientHttpRequestFactory();

            f.setConnectTimeout(5000);

            f.setReadTimeout(5000);

            RestTemplate rt = new RestTemplate(f);

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.setBearerAuth(token);

            Map<String, String> body = new HashMap<>();

            body.put("stack", stack);
            body.put("level", level);
            body.put("package", packageName);
            body.put("message", message);

            System.out.println("Sending log...");
            System.out.println(body);

            HttpEntity<Map<String, String>> entity =
                    new HttpEntity<>(body, headers);

            ResponseEntity<String> response =
                    rt.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            System.out.println(response.getBody());

            return response.getBody();

        } catch (Exception e) {

            return "LOG_ERROR : " + e.getMessage();
        }
    }
}

