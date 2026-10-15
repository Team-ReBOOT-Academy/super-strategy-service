package ru.omsu.superstrategyservice.health.adapter.in.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omsu.superstrategyservice.health.core.model.SystemHealthReport;
import ru.omsu.superstrategyservice.health.core.port.in.GetHealthStatusUseCase;

@RestController
public class HealthController {
    private final GetHealthStatusUseCase getHealthStatusUseCase;

    public HealthController(GetHealthStatusUseCase getHealthStatusUseCase) {
        this.getHealthStatusUseCase = getHealthStatusUseCase;
    }

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthResponseDto> getHealth() {
        SystemHealthReport report = getHealthStatusUseCase.execute();
        return ResponseEntity.ok(HealthResponseDto.fromDomain(report));
    }
}
