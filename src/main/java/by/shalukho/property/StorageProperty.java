package by.shalukho.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
public class StorageProperty {
    /**
     * Folder mainImageLocation for storing files
     */
    private String mainImageLocation = "/opt/soft/images/main";

    /**
     * Folder stockImageLocation for storing files
     */
    private String stockImageLocation = "/opt/soft/images/stock";

    public String getMainImageLocation() {
        return mainImageLocation;
    }

    public void setMainImageLocation(String mainImageLocation) {
        this.mainImageLocation = mainImageLocation;
    }
}
