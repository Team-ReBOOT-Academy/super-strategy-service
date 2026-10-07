package ru.omsu.superstrategyservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.omsu.superstrategyservice.dto.HealthResponseDto;
import ru.omsu.superstrategyservice.dto.HealthStatus;

import java.util.Collections;

@RestController
public class HealthController {

    @Value("${service.name}")
    private String serviceName;

    @Value("${service.version}")
    private String serviceVersion;

    @GetMapping(value="/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthResponseDto> getHealth() {
        HealthResponseDto response = new HealthResponseDto(
                HealthStatus.UP,
                serviceName,
                serviceVersion,
                Collections.emptyMap()
        );
        return ResponseEntity.ok(response);
    }
}
