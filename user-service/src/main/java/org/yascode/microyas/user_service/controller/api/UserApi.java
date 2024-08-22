package org.yascode.microyas.user_service.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.yascode.microyas.user_service.entity.User;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface UserApi {
    @GetMapping
    CompletionStage<List<User>> getAllUsers(@RequestParam(name = "state", defaultValue = "false", required = false) boolean state,
                                            @RequestParam(name = "retry", defaultValue = "false", required = false) boolean retry,
                                            @RequestParam(name = "timelimiter", defaultValue = "false", required = false) boolean timelimiter,
                                            @RequestParam(name = "sleep", defaultValue = "1", required = false) int sleep);

    @GetMapping(value = "/all")
    ResponseEntity<?> getUsers();

    User getUserById(@PathVariable Long id);

    User createUser(@RequestBody User user);

    User updateUser(@PathVariable Long id, @RequestBody User userDetails);

    void deleteUser(@PathVariable Long id);
}
