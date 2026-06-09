package com.project.vehicle_maintence_scheduler.service;

import com.project.vehicle_maintence_scheduler.client.VehicleClient;
import com.project.vehicle_maintence_scheduler.dto.Depot;
import com.project.vehicle_maintence_scheduler.dto.Vehicle;
import com.project.vehicle_maintence_scheduler.logging.LoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final VehicleClient vc;

    private final LoggingService ls;

    public List<Vehicle> optimize(int depotId) {

        ls.log(
                "backend",
                "info",
                "service",
                "Optimization started for depot " + depotId
        );

        List<Depot> d =
                vc.getDepots().getDepots();

        ls.log(
                "backend",
                "info",
                "client",
                "Depots fetched successfully"
        );

        int h = 0;

        for (Depot x : d) {

            if (x.getId() == depotId) {

                h = x.getMechanicHours();

                ls.log(
                        "backend",
                        "info",
                        "service",
                        "Mechanic hours found: " + h
                );
            }
        }

        List<Vehicle> v =
                vc.getVehicles().getVehicles();

        ls.log(
                "backend",
                "info",
                "client",
                "Vehicles fetched successfully"
        );

        int n = v.size();

        int[][] dp =
                new int[n + 1][h + 1];

        for (int i = 1; i <= n; i++) {

            Vehicle c = v.get(i - 1);

            for (int j = 0; j <= h; j++) {

                dp[i][j] = dp[i - 1][j];

                if (c.getDuration() <= j) {

                    dp[i][j] = Math.max(
                            dp[i][j],
                            c.getImpact()
                                    + dp[i - 1][j - c.getDuration()]
                    );
                }
            }
        }

        ls.log(
                "backend",
                "info",
                "service",
                "DP optimization completed"
        );

        List<Vehicle> ans =
                new ArrayList<>();

        int j = h;

        for (int i = n; i > 0; i--) {

            if (dp[i][j] != dp[i - 1][j]) {

                Vehicle c = v.get(i - 1);

                ans.add(c);

                j -= c.getDuration();
            }
        }

        ls.log(
                "backend",
                "info",
                "service",
                "Selected vehicles count: " + ans.size()
        );

        return ans;
    }
}