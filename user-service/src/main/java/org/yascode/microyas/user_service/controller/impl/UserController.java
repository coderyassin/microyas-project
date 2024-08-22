package org.yascode.microyas.user_service.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.yascode.microyas.user_service.controller.api.UserApi;
import org.yascode.microyas.user_service.entity.User;
import org.yascode.microyas.user_service.service.UserService;

import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CompletionStage<List<User>> getAllUsers(boolean state, boolean retry, boolean timelimiter, int sleep) {
        log.info("Get all users");
        return userService.getAllUsers(state, retry, timelimiter, sleep)
                .thenApply(users -> {
                    log.info("Received users: [{}]", users.size());
                    return users;
                });
    }

    @Override
    public ResponseEntity<?> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:9095/users",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Object.class);
        return responseEntity;
    }

    @Override
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}
