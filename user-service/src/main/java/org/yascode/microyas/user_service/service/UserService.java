package org.yascode.microyas.user_service.service;

import org.yascode.microyas.user_service.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers(boolean state);
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}
