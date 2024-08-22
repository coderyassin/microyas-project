package org.yascode.microyas.user_service.exception.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ErrorMessage {
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String message;
    private String description;
}