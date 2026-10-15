package ru.omsu.superstrategyservice.health.adapter.in.web;

import ru.omsu.superstrategyservice.health.core.model.SystemHealthReport;

import java.util.Map;
import java.util.stream.Collectors;

public record HealthResponseDto(
        String status,
        String service,
        String version,
        Map<String, ComponentHealthDto> components
) {
    public static HealthResponseDto fromDomain(SystemHealthReport report) {
        Map<String, ComponentHealthDto> componentDtos = report.components().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ComponentHealthDto.fromDomain(entry.getValue())
                ));

        return new HealthResponseDto(
                report.status().name(),
                report.serviceName(),
                report.version(),
                componentDtos
        );
    }
}
