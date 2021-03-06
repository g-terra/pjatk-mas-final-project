package pjatk.mas.finalproject.devicemanufactureapi.api.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {
    /**
     * Get endpoint for health check of the API.
     * @return HealthCheckResponse - response with status OK
     */
    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }

}
