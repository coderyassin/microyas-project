package org.yascode.microyas.project_service.client.impl.standard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.yascode.microyas.project_service.client.UserClient;

@Component
@Slf4j
public class UserClientImpl implements UserClient {
    private final RestTemplate restTemplate;

    public UserClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Object allUsers() {
        try {
            RequestEntity<?> requestEntity = RequestEntity.get("http://USER-SERVICE/users/all").build();
            ResponseEntity<Object> responseEntity = restTemplate.exchange(requestEntity, Object.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
