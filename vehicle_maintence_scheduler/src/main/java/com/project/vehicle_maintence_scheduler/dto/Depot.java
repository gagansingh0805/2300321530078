package com.project.vehicle_maintence_scheduler.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Depot {

    private int id;
    private int mechanicHours;
}
