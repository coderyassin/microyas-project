package org.yascode.microyas.gateway_service.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.microyas.gateway_service.controller.api.FallbackApi;

@RestController
@RequestMapping("/fallback")
public class FallbackController implements FallbackApi {

    @Override
    public ResponseEntity<String> service1Fallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Service 1 is currently unavailable. Please try again later.");
    }
}
