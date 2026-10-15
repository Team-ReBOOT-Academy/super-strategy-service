package ru.omsu.superstrategyservice.health.application;

import org.junit.jupiter.api.Test;
import ru.omsu.superstrategyservice.health.core.model.ComponentHealth;
import ru.omsu.superstrategyservice.health.core.model.ComponentStatus;
import ru.omsu.superstrategyservice.health.core.model.ServiceStatus;
import ru.omsu.superstrategyservice.health.core.model.SystemHealthReport;
import ru.omsu.superstrategyservice.health.core.port.out.ComponentHealthIndicator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthCheckServiceTest {
    @Test
    void shouldReturnDegradedWhenAtLeastOneComponentIsDown() {
        ComponentHealthIndicator okIndicator = () -> ComponentHealth.up("runtime");
        ComponentHealthIndicator downIndicator = () -> ComponentHealth.down("database", "connection unavailable");

        HealthCheckService service = new HealthCheckService(
                "super-strategy-service",
                "1.0.0",
                List.of(okIndicator, downIndicator)
        );

        SystemHealthReport report = service.execute();

        assertEquals(ServiceStatus.DEGRADED, report.status());
        assertEquals(ComponentStatus.UP, report.components().get("runtime").status());
        assertEquals(ComponentStatus.DOWN, report.components().get("database").status());
    }
}
