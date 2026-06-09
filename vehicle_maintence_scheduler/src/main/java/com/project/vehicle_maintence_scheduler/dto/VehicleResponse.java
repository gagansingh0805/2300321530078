package com.project.vehicle_maintence_scheduler.dto;

import lombok.Data;

import java.util.List;

@Data
public class VehicleResponse {

    private List<Vehicle> vehicles;
}
