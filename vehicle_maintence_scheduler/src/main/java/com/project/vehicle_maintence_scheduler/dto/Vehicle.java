package com.project.vehicle_maintence_scheduler.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {

    private String taskId;

    private int duration;

    private int impact;
}
