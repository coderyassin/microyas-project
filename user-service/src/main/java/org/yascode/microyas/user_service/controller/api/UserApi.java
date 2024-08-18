package org.yascode.microyas.user_service.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.yascode.microyas.user_service.entity.User;

public interface UserApi {
    @GetMapping
    ResponseEntity<?> getAllUsers();

    @GetMapping(value = "/all")
    ResponseEntity<?> getUsers();

    User getUserById(@PathVariable Long id);

    User createUser(@RequestBody User user);

    User updateUser(@PathVariable Long id, @RequestBody User userDetails);

    void deleteUser(@PathVariable Long id);
}
