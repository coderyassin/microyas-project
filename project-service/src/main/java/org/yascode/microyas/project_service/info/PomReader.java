package org.yascode.microyas.project_service.info;

import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Model;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PomReader {
    private final Model pomModel;

    public PomReader(Model pomModel) {
        this.pomModel = pomModel;
    }

    public String getArtifactId() {
        try {
            return pomModel.getArtifactId();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown artifactId";
        }
    }

    public String getAppVersion() {
        try {
            return pomModel.getVersion();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown version";
        }
    }

    public String getAppDescription() {
        try {
            return pomModel.getDescription();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown description";
        }
    }
}
