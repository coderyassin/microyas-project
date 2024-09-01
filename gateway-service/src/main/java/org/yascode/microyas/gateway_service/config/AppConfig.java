package org.yascode.microyas.gateway_service.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.Reader;

@Configuration
@Slf4j
public class AppConfig {
    private final static String POM_FILE = "gateway-service/pom.xml";

    @Bean
    public Model pomModel() {
        try {
            Reader reader = new FileReader(POM_FILE);
            MavenXpp3Reader mavenReader = new MavenXpp3Reader();
            return mavenReader.read(reader);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
