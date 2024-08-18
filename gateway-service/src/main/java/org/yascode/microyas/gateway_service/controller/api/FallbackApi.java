package org.yascode.microyas.gateway_service.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface FallbackApi {
    @GetMapping("/user-service")
    ResponseEntity<String> service1Fallback();
}
