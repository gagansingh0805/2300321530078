package com.project.vehicle_maintence_scheduler.client;

import com.project.vehicle_maintence_scheduler.dto.DepotResponse;
import com.project.vehicle_maintence_scheduler.dto.VehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class VehicleClient {

    private final RestTemplate rt;

    private static final String DEPOT_URL =
            "http://4.224.186.213/evaluation-service/depots";

    private static final String VEHICLE_URL =
            "http://4.224.186.213/evaluation-service/vehicles";

    private static final String TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiYXVkIjoiaHR0cDovLzIwLjI0NC41Ni4xNDQvZXZhbHVhdGlvbi1zZXJ2aWNlIiwiZW1haWwiOiJnYWdhbnNpbmdoMjAwNS44QGdtYWlsLmNvbSIsImV4cCI6MTc4MDk5MjAwNSwiaWF0IjoxNzgwOTkxMTA1LCJpc3MiOiJBZmZvcmQgTWVkaWNhbCBUZWNobm9sb2dpZXMgUHJpdmF0ZSBMaW1pdGVkIiwianRpIjoiM2QwYmVhMDctMmU4My00OTkxLTgxMzEtNDJkYjAwMDQwMDdjIiwibG9jYWxlIjoiZW4tSU4iLCJuYW1lIjoiZ2FnYW4gc2luZ2giLCJzdWIiOiIwOTk0NTJjZS1jNTQxLTQ1ZDItOWVlMC0yY2Q5NDQ4YzU3MmIifSwiZW1haWwiOiJnYWdhbnNpbmdoMjAwNS44QGdtYWlsLmNvbSIsIm5hbWUiOiJnYWdhbiBzaW5naCIsInJvbGxObyI6IjIzMDAzMjE1MzAwNzgiLCJhY2Nlc3NDb2RlIjoiY1h1cWh0IiwiY2xpZW50SUQiOiIwOTk0NTJjZS1jNTQxLTQ1ZDItOWVlMC0yY2Q5NDQ4YzU3MmIiLCJjbGllbnRTZWNyZXQiOiJ0SnhZZ1ZEV3ZVZndSelROIn0.MxMoWlBih_Eo1pNXQZ89YY1Bbu-GzCieqvsg_-Ats8c";
    public DepotResponse getDepots() {

        HttpHeaders h =
                new HttpHeaders();

        h.setBearerAuth(TOKEN);

        HttpEntity<String> e =
                new HttpEntity<>(h);

        ResponseEntity<DepotResponse> r =
                rt.exchange(
                        DEPOT_URL,
                        HttpMethod.GET,
                        e,
                        DepotResponse.class
                );

        return r.getBody();
    }

    public VehicleResponse getVehicles() {

        HttpHeaders h =
                new HttpHeaders();

        h.setBearerAuth(TOKEN);

        HttpEntity<String> e =
                new HttpEntity<>(h);

        ResponseEntity<VehicleResponse> r =
                rt.exchange(
                        VEHICLE_URL,
                        HttpMethod.GET,
                        e,
                        VehicleResponse.class
                );

        return r.getBody();
    }
}