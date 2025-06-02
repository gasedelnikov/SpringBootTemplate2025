package ru.gri.core;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi managementOpenApi() {
        String[] paths = {"/template/**", "/auth/**"};
        return GroupedOpenApi.builder()
                .addOpenApiCustomizer(a -> a.info(
                        new Info()
                                .title("Template API")
                                .description("Template API Documentation")
                                .version("V1")))
                .group("Template")
                .pathsToMatch(paths)
                .displayName("Template API")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .components(new Components()
//                        .addSecuritySchemes("basicScheme",
//                                new SecurityScheme()
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("basic"))
                        .addSecuritySchemes("bearerScheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

}
