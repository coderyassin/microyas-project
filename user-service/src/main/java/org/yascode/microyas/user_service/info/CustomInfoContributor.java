package org.yascode.microyas.user_service.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {
    private final VersionService versionService;

    public CustomInfoContributor(VersionService versionService) {
        this.versionService = versionService;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("Info app", Map.of(
                "name", "user-service",
                "description", "The service that manages users",
                "version", versionService.getVersion()
        ));
    }
}
