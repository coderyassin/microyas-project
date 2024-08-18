package org.yascode.microyas.gateway_service.filter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.yascode.microyas.gateway_service.config.LoggingGatewayConfig;
import org.yascode.microyas.gateway_service.enums.LogLevel;

import java.util.Objects;

@Component
@Slf4j
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {

    private final LoggingGatewayConfig loggingGatewayConfig;

    public LoggingGatewayFilterFactory(LoggingGatewayConfig loggingGatewayConfig) {
        super(LoggingGatewayFilterFactory.Config.class);
        this.loggingGatewayConfig = loggingGatewayConfig;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            String logMessage = createLogMessage(request, loggingGatewayConfig);

            logBasedOnLevel(logMessage, loggingGatewayConfig.getLogLevel());

            return chain.filter(exchange);
        };
    }

    private String createLogMessage(ServerHttpRequest request, LoggingGatewayConfig loggingGatewayConfig) {
        StringBuilder logMessage = new StringBuilder("Request Path: ").append(request.getPath());

        if (Objects.nonNull(loggingGatewayConfig.isIncludeHeaders()) && loggingGatewayConfig.isIncludeHeaders()) {
            logMessage.append(", Headers: ").append(request.getHeaders());
        }

        if (Objects.nonNull(loggingGatewayConfig.isIncludeBody()) && loggingGatewayConfig.isIncludeBody()) {
            logMessage.append(", Body: [body logging not implemented]");
        }

        return logMessage.toString();
    }

    private void logBasedOnLevel(String logMessage, String logLevel) {
        LogLevel level = LogLevel.fromString(logLevel);
        switch (level) {
            case DEBUG:
                log.debug(logMessage);
                break;
            case WARN:
                log.warn(logMessage);
                break;
            case ERROR:
                log.error(logMessage);
                break;
            default:
                log.info(logMessage);
        }
    }

    @Getter
    @Setter
    public static class Config {
        private Boolean includeHeaders;
        private Boolean includeBody;
        private String logLevel;
    }
}
