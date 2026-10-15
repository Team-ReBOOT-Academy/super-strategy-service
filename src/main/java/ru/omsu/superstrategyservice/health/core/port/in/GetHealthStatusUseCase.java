package ru.omsu.superstrategyservice.health.core.port.in;

import ru.omsu.superstrategyservice.health.core.model.SystemHealthReport;

public interface GetHealthStatusUseCase {
    SystemHealthReport execute();
}
