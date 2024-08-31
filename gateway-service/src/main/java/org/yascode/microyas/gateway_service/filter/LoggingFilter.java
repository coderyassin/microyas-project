package org.yascode.microyas.gateway_service.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("Filter executed for query: {}", exchange.getRequest().getURI());
        return chain.filter(exchange)
                .doOnSuccess(aVoid -> log.info("Success"));
    }

}
