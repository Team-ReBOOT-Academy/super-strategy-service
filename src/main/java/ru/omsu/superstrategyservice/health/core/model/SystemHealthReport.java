package ru.omsu.superstrategyservice.health.core.model;

import java.util.Map;

public record SystemHealthReport(
        ServiceStatus status,
        String serviceName,
        String version,
        Map<String, ComponentHealth> components
) {
    public static SystemHealthReport create(String serviceName, String version, Map<String, ComponentHealth> components) {
        boolean isDegraded = components.values().stream()
                .anyMatch(
                        component -> component.status() != ComponentStatus.UP
                );

        ServiceStatus overallStatus = isDegraded ? ServiceStatus.DEGRADED : ServiceStatus.UP;
        return new SystemHealthReport(overallStatus, serviceName, version, Map.copyOf(components));
    }
}
