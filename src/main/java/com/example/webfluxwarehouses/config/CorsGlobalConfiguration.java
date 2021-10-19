package com.example.webfluxwarehouses.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Configuration class for further frontend integration
 */
@Configuration
public class CorsGlobalConfiguration implements WebFluxConfigurer {

    /**
     * method which defines which operations are accessible for the frontend
     * @param corsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "GET", "POST", "DELETE")
                .maxAge(3600);
    }
}
