package org.yascode.microyas.user_service.info;

import lombok.experimental.UtilityClass;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.FileReader;
import java.io.Reader;

@UtilityClass
public class PomReader {
    public String getAppVersion() {
        try {
            Reader reader = new FileReader("user-service/pom.xml");
            MavenXpp3Reader mavenReader = new MavenXpp3Reader();
            Model model = mavenReader.read(reader);
            return model.getVersion();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown version";
        }
    }
}
