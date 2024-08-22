package org.yascode.microyas.user_service.service;

import org.yascode.microyas.user_service.entity.User;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface UserService {
    CompletionStage<List<User>> getAllUsers(boolean state, boolean retry, boolean timelimiter, int sleep);
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}
