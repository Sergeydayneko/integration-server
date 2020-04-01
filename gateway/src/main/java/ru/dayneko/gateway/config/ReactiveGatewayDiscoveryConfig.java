package ru.dayneko.gateway.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
public class ReactiveGatewayDiscoveryConfig {

    @Bean
    public DiscoveryClientRouteDefinitionLocator discoveryClientRouteLocator(
            ReactiveDiscoveryClient discoveryClient,
            DiscoveryLocatorProperties discoveryLocatorProperties) {

        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, discoveryLocatorProperties);
    }
}