package org.yascode.microyas.project_service.service.impl.standard;

import org.springframework.stereotype.Service;
import org.yascode.microyas.project_service.client.UserClient;
import org.yascode.microyas.project_service.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserClient userClient;

    public UserServiceImpl(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public Object allUsers() {
        return userClient.allUsers();
    }
}
