package ru.omsu.superstrategyservice.health.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.omsu.superstrategyservice.health.core.model.ComponentHealth;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ComponentHealthDto(
        String status,
        String details
) {
    public static ComponentHealthDto fromDomain(ComponentHealth domain) {
        return new ComponentHealthDto(
                domain.status().name(),
                domain.details()
        );
    }
}
