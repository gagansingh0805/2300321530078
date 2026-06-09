package com.project.logging_middleware.service;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final String AUTH_URL =
            "http://4.224.186.213/evaluation-service/auth";

    public String getToken() {

        try {

            System.out.println("Generating token...");

            SimpleClientHttpRequestFactory f =
                    new SimpleClientHttpRequestFactory();

            f.setConnectTimeout(5000);

            f.setReadTimeout(5000);

            RestTemplate rt = new RestTemplate(f);

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();

            body.put(
                    "email",
                    "gagansingh2005.8@gmail.com"
            );

            body.put(
                    "name",
                    "gagan singh"
            );

            body.put(
                    "rollNo",
                    "2300321530078"
            );

            body.put(
                    "accessCode",
                    "cXuqht"
            );

            body.put(
                    "clientID",
                    "099452ce-c541-45d2-9ee0-2cd9448c572b"
            );

            body.put(
                    "clientSecret",
                    "tJxYgVDWvUfwRzTN"
            );

            HttpEntity<Map<String, String>> entity =
                    new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    rt.exchange(
                            AUTH_URL,
                            HttpMethod.POST,
                            entity,
                            Map.class
                    );

            System.out.println(response.getBody());

            return response
                    .getBody()
                    .get("access_token")
                    .toString();

        } catch (Exception e) {

            return "TOKEN_ERROR : " + e.getMessage();
        }
    }
}

