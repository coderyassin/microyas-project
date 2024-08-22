package org.yascode.microyas.user_service.exception.predicate;

import org.yascode.microyas.user_service.exception.ResourceNotFoundException;

import java.util.function.Predicate;

public class RecordFailurePredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        return (throwable instanceof NullPointerException);
    }
}
