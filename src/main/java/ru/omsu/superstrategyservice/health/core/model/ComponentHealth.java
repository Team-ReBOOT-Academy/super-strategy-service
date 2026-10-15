package ru.omsu.superstrategyservice.health.core.model;

public record ComponentHealth(
    String name,
    ComponentStatus status,
    String details
) {
    public static ComponentHealth up(String name) {
        return new ComponentHealth(name, ComponentStatus.UP, null);
    }

    public static ComponentHealth up(String name, String details) {
        return new ComponentHealth(name, ComponentStatus.UP, details);
    }

    public static ComponentHealth down(String name, String details) {
        return new ComponentHealth(name, ComponentStatus.DOWN, details);
    }

    public static ComponentHealth unknown(String name, String details) {
        return new ComponentHealth(name, ComponentStatus.UNKNOWN, details);
    }
}
