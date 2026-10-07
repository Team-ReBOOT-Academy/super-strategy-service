package ru.omsu.superstrategyservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ComponentHealthDto(
    HealthStatus status,
    String details
) {
    public ComponentHealthDto(HealthStatus status) {
        this(status, null);
    }
}
