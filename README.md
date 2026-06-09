# Vehicle Maintenance Scheduler

1. This repository contains two Spring Boot applications.

2. `vehicle_maintence_scheduler` optimizes vehicle maintenance schedules.

3. `logging_middleware` handles authenticated logging and forwards logs to the external evaluation API.

4. Client sends request to:

   ```http
   GET /api/schedule/{id}
   ```

5. `SchedulerController` receives the request.

6. `SchedulerService.optimize(id)` processes the request.

7. Scheduler fetches depot data from external APIs.

8. Scheduler fetches vehicle data from external APIs.

9. Depot `mechanicHours` are used as available maintenance capacity.

10. Dynamic Programming (0/1 Knapsack) algorithm is used for optimization.

11. `duration` represents maintenance time required for a vehicle.

12. `impact` represents maintenance benefit/value.

13. Goal is to maximize total impact within available mechanic hours.

14. Optimized vehicle list is returned as API response.

15. Scheduler sends logs to:

```http
POST http://localhost:8081/logs
```

16. Logging middleware receives log payloads.

17. Middleware generates authentication token.

18. Middleware forwards logs to external evaluation logging API.

19. Example log payload:

```json
{
  "stack": "backend",
  "level": "info",
  "package": "service",
  "message": "Optimization started for depot 1"
}
```

20. Main scheduler endpoint:

```http
GET /api/schedule/{id}
```

21. Main logging endpoint:

```http
POST /logs
```

22. Start middleware service first:

```bash
cd logging_middleware
./mvnw spring-boot:run
```

23. Start scheduler service:

```bash
cd vehicle_maintence_scheduler
./mvnw spring-boot:run
```

24. Test API using:

```bash
curl http://localhost:8080/api/schedule/1
```

25. `VehicleClient` imports `AuthService`.

26. `AuthService` is currently missing in scheduler module.

27. Project may fail during compilation or startup because of missing `AuthService`.

28. Technologies used:

* Java
* Spring Boot
* Maven
* RestTemplate
* Lombok
* Dynamic Programming
