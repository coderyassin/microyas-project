package org.yascode.microyas.user_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomCircuitBreakerAspect {
    @Pointcut("@annotation(io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker)")
    public void anyAnnotatedMethod() {
    }

    //@Around("@annotation(io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker)")
    public Object aroundAnyAnnotatedMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        }
    }
}
