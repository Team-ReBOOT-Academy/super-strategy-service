package ru.omsu.superstrategyservice.health.adapter.out;

import org.springframework.stereotype.Component;
import ru.omsu.superstrategyservice.health.core.model.ComponentHealth;
import ru.omsu.superstrategyservice.health.core.port.out.ComponentHealthIndicator;

@Component
public class RuntimeSelfCheckAdapter implements ComponentHealthIndicator {
    private static final String COMPONENT_NAME = "runtime";
    private static final double MIN_FREE_MEMORY_RATIO = 0.10;

    @Override
    public ComponentHealth check() {
        Runtime runtime = Runtime.getRuntime();

        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long totalFreeMemory = freeMemory + (maxMemory - allocatedMemory);

        double freeRatio = (double) totalFreeMemory / maxMemory;

        if (freeRatio < MIN_FREE_MEMORY_RATIO) {
            return ComponentHealth.down(COMPONENT_NAME, "low memory available");
        }

        return ComponentHealth.up(COMPONENT_NAME, "memory and threads healthy");
    }
}
