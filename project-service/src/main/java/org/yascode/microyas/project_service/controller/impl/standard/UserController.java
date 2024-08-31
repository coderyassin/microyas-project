package org.yascode.microyas.project_service.controller.impl.standard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.microyas.project_service.controller.api.UserApi;
import org.yascode.microyas.project_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> allUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }
}
