package org.yascode.microyas.user_service.exception;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.yascode.microyas.user_service.exception.response.ErrorMessage;

import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.warn("ResourceNotFoundException exception message: {}", ex.getMessage());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return errorMessage;
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        log.warn("NoSuchElementException exception message: {}", ex.getMessage());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return errorMessage;
    }

    @ExceptionHandler(value = {RequestNotPermitted.class})
    @ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS)
    public ErrorMessage handleRequestNotPermitted(RequestNotPermitted ex, WebRequest request) {
        log.warn("RequestNotPermitted exception message: {}", ex.getMessage());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.TOO_MANY_REQUESTS)
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return errorMessage;
    }

    @ExceptionHandler(value = {TimeoutException.class})
    @ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
    public ErrorMessage handleTimeoutException(TimeoutException ex, WebRequest request) {
        log.warn("A TimeoutException occurred: {}", ex.getMessage());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(HttpStatus.GATEWAY_TIMEOUT)
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return errorMessage;
    }

}
