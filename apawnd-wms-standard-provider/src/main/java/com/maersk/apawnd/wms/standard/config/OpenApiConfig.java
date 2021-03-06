package com.maersk.apawnd.wms.standard.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
        .components(new Components())
        .info(new Info()
                .title("WMS Standard APIs")
                .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
  }
}
