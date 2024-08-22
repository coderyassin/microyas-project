package org.yascode.microyas.user_service.util;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class CircuitBreakerUtils {
    private static final String RECORD_EXCEPTIONS = "recordExceptions";
    private static final Field RECORD_EXCEPTIONS_FIELD;

    static {
        try {
            RECORD_EXCEPTIONS_FIELD = CircuitBreakerConfig.class.getDeclaredField(RECORD_EXCEPTIONS);
            RECORD_EXCEPTIONS_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            log.error("Failed to access recordExceptions field", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public Set<Class<? extends Throwable>> getRecordExceptions(CircuitBreakerConfig config) {
        try {
            Class<? extends Throwable>[] exceptions = (Class<? extends Throwable>[]) RECORD_EXCEPTIONS_FIELD.get(config);
            return Collections.unmodifiableSet(Arrays.stream(exceptions).collect(Collectors.toSet()));
        } catch (IllegalAccessException e) {
            log.error("Failed to access recordExceptions value", e);
            return Collections.emptySet();
        }
    }
}
