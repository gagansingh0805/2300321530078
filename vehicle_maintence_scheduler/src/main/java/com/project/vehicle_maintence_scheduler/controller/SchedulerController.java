package com.project.vehicle_maintence_scheduler.controller;

import com.project.vehicle_maintence_scheduler.dto.Vehicle;
import com.project.vehicle_maintence_scheduler.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SchedulerController {

    private final SchedulerService ss;

    @GetMapping("/schedule/{id}")
    public List<Vehicle> schedule(
            @PathVariable int id
    ) {

        return ss.optimize(id);
    }
}