package org.yascode.microyas.user_service.exception;

public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException(String msg) {
        super(msg);
    }

    public NoSuchElementException(String msg, Throwable cause) {
        super(msg, cause);
    }
}