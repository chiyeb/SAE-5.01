package com.example.saebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*") // autorise toutes les origines, prendre soin de modifier lors du déploiement
                    .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
                    .allowedHeaders("*");
        }
}
