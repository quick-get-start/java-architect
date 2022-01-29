package com.start.quick.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {

    private String imageUserLocation;

    public String getImageUserLocation() {
        return imageUserLocation;
    }

    public void setImageUserLocation(String imageUserLocation) {
        this.imageUserLocation = imageUserLocation;
    }
}
