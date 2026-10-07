package ru.omsu.superstrategyservice.dto;

import java.util.Map;

public record HealthResponseDto(
    HealthStatus status,
    String service,
    String version,
    Map<String, ComponentHealthDto> components
) {}
