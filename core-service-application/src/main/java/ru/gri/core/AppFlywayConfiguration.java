package ru.gri.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {AppFlywayProperties.class})
public class AppFlywayConfiguration {

    private final AppFlywayProperties commonProperties;

    @Autowired
    public AppFlywayConfiguration(final AppFlywayProperties commonProperties) {
        this.commonProperties = commonProperties;
    }

    @Bean(name = "isCleanInstall")
    public boolean isCleanInstall() {
        return commonProperties.isCleanInstall();
    }

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy(@Autowired @Qualifier(value = "isCleanInstall") boolean isCleanInstall) {
        return flyway -> {
            if (isCleanInstall) {
                flyway.clean();
            }
            flyway.migrate();
        };
    }

}
