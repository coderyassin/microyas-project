package org.yascode.microyas.project_service.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserApi {
    @GetMapping
     ResponseEntity<?> allUsers();
}
