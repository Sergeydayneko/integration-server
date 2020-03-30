package ru.dayneko.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RouteLocator gatewayRoutesConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/api/v1/store/**")
                        .filters(f -> f.rewritePath("/api/v1/store/", "/store/brand?brandId="))
                        .uri("http://localhost:4041/store/")
                        .id("store-point-service"))
                .build();
    }
}
