package ru.omsu.superstrategyservice.health.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.omsu.superstrategyservice.health.core.model.ComponentHealth;
import ru.omsu.superstrategyservice.health.core.model.SystemHealthReport;
import ru.omsu.superstrategyservice.health.core.port.in.GetHealthStatusUseCase;
import ru.omsu.superstrategyservice.health.core.port.out.ComponentHealthIndicator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class HealthCheckService implements GetHealthStatusUseCase {
    private final String serviceName;
    private final String version;
    private final List<ComponentHealthIndicator> indicators;

    public HealthCheckService(
            @Value("${spring.application.name}") String serviceName,
            @Value("${app.version}") String version,
            List<ComponentHealthIndicator> indicators
    ) {
        this.serviceName = serviceName;
        this.version = version;
        this.indicators = indicators;
    }

    @Override
    public SystemHealthReport execute() {
        Map<String, ComponentHealth> components = new LinkedHashMap<>();

        for (ComponentHealthIndicator indicator : indicators) {
            ComponentHealth health = safeCheck(indicator);
            components.put(health.name(), health);
        }

        return SystemHealthReport.create(serviceName, version, components);
    }

    private ComponentHealth safeCheck(ComponentHealthIndicator indicator) {
        try {
            return indicator.check();
        } catch (Exception e) {
            return ComponentHealth.down(
                    indicator.getClass().getSimpleName(),
                    "Health check failed"
            );
        }
    }
}
