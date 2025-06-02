package ru.gri.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "core-service-api")
public class AppRestProperties {

    private String apiPath;

    private String apiVersion;

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
}
