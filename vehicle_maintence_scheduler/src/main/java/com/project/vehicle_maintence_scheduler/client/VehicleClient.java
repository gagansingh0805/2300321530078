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

    private static final String TOKEN_URL =
            "http://localhost:8081/token";

    public DepotResponse getDepots() {

        String token =
                rt.getForObject(
                        TOKEN_URL,
                        String.class
                );

        HttpHeaders h =
                new HttpHeaders();

        h.setBearerAuth(token);

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

        String token =
                rt.getForObject(
                        TOKEN_URL,
                        String.class
                );

        HttpHeaders h =
                new HttpHeaders();

        h.setBearerAuth(token);

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