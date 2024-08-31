package org.yascode.microyas.project_service.info;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CustomInfoContributor {
    private final PomReader pomReader;

    public CustomInfoContributor(PomReader pomReader) {
        this.pomReader = pomReader;
    }

    @Bean
    public InfoContributor buildInfoContributor() {
        return builder -> {
            builder.withDetail("Info app", Map.of(
                    "name", pomReader.getArtifactId(),
                    "description", pomReader.getAppDescription(),
                    "version", pomReader.getAppVersion()
            ));
        };
    }
}
