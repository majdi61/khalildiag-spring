package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "https://khalildiag-web-admin.web.app/",
                        "https://khalildiag-2a0e5.web.app/",
                        "https://khalildiag.com/",
                        "http://127.0.0.1:5500/"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Explicitly list methods
                .allowedHeaders("Content-Type", "Authorization", "Accept", "X-Requested-With") // Include necessary headers
                .exposedHeaders("X-Total-Count", "X-Auth-Token") // Exposing these headers to the client
                .allowCredentials(false)
                .maxAge(3600);
    }
}