package ru.omsu.superstrategyservice.health.core.port.out;

import ru.omsu.superstrategyservice.health.core.model.ComponentHealth;

public interface ComponentHealthIndicator {
    ComponentHealth check();
}
