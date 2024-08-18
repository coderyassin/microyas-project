package org.yascode.microyas.gateway_service.enums;

public enum LogLevel {
    DEBUG, INFO, WARN, ERROR;

    public static LogLevel fromString(String logLevel) {
        try {
            return LogLevel.valueOf(logLevel.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INFO;
        }
    }
}
