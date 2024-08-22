package org.yascode.microyas.user_service.service.impl;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.yascode.microyas.user_service.entity.User;
import org.yascode.microyas.user_service.exception.NoSuchElementException;
import org.yascode.microyas.user_service.exception.ResourceNotFoundException;
import org.yascode.microyas.user_service.repository.UserRepository;
import org.yascode.microyas.user_service.service.UserService;
import org.yascode.microyas.user_service.util.CircuitBreakerUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private static final String SERVICE_NAME = "user-service";
    private static final int DEFAULT_SIZE = 10;
    private static final boolean SERVICE_STATUS = true;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final UserRepository userRepository;

    public UserServiceImpl(CircuitBreakerRegistry circuitBreakerRegistry,
                           UserRepository userRepository) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.userRepository = userRepository;
    }

    @Override
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "getUsersFallbackMethod")
    @RateLimiter(name = SERVICE_NAME, fallbackMethod = "getUsersFallbackMethod")
    public List<User> getAllUsers(boolean state) {
        log.info("Attempting to get all users");
        int failureRate = new Random().nextInt(100);

        if(state) {
            return userRepository.findAll();
        } else {
            if (failureRate < 20) {
                log.warn("Simulating a failure in getAllUsers");
                throw new ResourceNotFoundException("Users not found");
            } else if(failureRate > 19 && failureRate < 50) {
                throw new NoSuchElementException("Element does not exist.");
            } else if(failureRate > 49 && failureRate < 95) {
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return userRepository.findAll();
            }
        }
    }

    private List<User> getUsersFallbackMethod(Throwable throwable) throws Throwable {
        log.warn("Fallback method called due to: {}", throwable.getMessage());

        if (shouldIgnoreException(SERVICE_NAME, throwable)) {
            log.info("Exception {} is ignored by Circuit Breaker. Re-throwing.", throwable.getClass().getSimpleName());
            throw throwable;
        }

        log.info("Returning fallback user");
        return generatesDefaultUsers(DEFAULT_SIZE);
    }



    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    private boolean shouldIgnoreException(String circuitBreakerName, Throwable throwable) {
        return Optional.ofNullable(circuitBreakerRegistry.circuitBreaker(circuitBreakerName))
                .map(cb -> {
                    CircuitBreakerConfig circuitBreakerConfig = cb.getCircuitBreakerConfig();
                    Set<Class<? extends Throwable>> recordExceptions = CircuitBreakerUtils.getRecordExceptions(circuitBreakerConfig);
                    boolean isRecordException = recordExceptions.stream()
                            .anyMatch(r -> r.isAssignableFrom(throwable.getClass()));
                    return !isRecordException;
                })
                .orElse(false);
    }

    private List<User> generatesDefaultUsers(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> User.builder()
                        .id(-1L * (i + 1))
                        .username("XXXXX")
                        .email("XXXXX")
                        .build())
                .toList();
    }

}
