package ru.gri.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "core-service-flyway")
public class AppFlywayProperties {

    @Value("${spring.flyway.clean-install}")
    private boolean cleanInstall;

    public boolean isCleanInstall() {
        return cleanInstall;
    }

}
