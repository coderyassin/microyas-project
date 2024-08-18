package org.yascode.microyas.gateway_service.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "logging.filter")
@Getter
@Setter
@Slf4j
public class LoggingGatewayConfig {
    private boolean includeHeaders;
    private boolean includeBody;
    private String logLevel;
}
