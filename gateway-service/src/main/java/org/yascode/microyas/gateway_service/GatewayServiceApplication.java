package org.yascode.microyas.gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.yascode.microyas.gateway_service.config.LoggingGatewayConfig;

@SpringBootApplication
@EnableConfigurationProperties(LoggingGatewayConfig.class)
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

}
