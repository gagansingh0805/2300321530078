package com.project.vehicle_maintence_scheduler.service;

import com.project.vehicle_maintence_scheduler.client.VehicleClient;
import com.project.vehicle_maintence_scheduler.dto.Depot;
import com.project.vehicle_maintence_scheduler.dto.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final VehicleClient vc;

    public List<Vehicle> optimize(int depotId) {

        List<Depot> d =
                vc.getDepots().getDepots();

        int h = 0;

        for (Depot x : d) {

            if (x.getId() == depotId) {

                h = x.getMechanicHours();
            }
        }

        List<Vehicle> v =
                vc.getVehicles().getVehicles();

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

        return ans;
    }
}
