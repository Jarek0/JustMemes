package edu.pl.pollub;

/**
 * Created by Dell on 2017-02-11.
 */
import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties("storage")
public class StorageProperties {
    /**
     * Folder location for storing files
     */
    private String location = "upload-files";
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
