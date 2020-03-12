package ru.dayneko.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    // todo draft // w8ing 4 bff servers
    @Bean
    public RouteLocator gatewayRoutesConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/api/v1/product/bff/**")
                        .filters(f -> f.addRequestHeader("proxy-applied", "true")
                                .redirect(302, "/product/bff"))
                        .uri("http://bff-server/")
                        .id("product-bff"))
                .build();
    }
}
