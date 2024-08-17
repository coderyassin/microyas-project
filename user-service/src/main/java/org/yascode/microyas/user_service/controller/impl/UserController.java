package org.yascode.microyas.user_service.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.microyas.user_service.controller.api.UserApi;
import org.yascode.microyas.user_service.entity.User;
import org.yascode.microyas.user_service.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
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
