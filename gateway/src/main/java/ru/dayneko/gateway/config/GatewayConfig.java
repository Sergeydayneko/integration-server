package ru.dayneko.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dayneko.gateway.model.ServerConfig;

import java.util.Map;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final Map<String, ServerConfig> serversConfigMap;

    private final Set<String> serversList;

    @Value("${gateway.tail-path}")
    private String globalTailPathRegex;

    @Value("${gateway.tail-regex}")
    private String globalRegexTailPath;

    @Value("${gateway.tail-regex-replacement}")
    private String globalTailRegexReplacement;

    @Bean
    public RouteLocator gatewayRoutesConfig(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        serversList.forEach(name -> {
            ServerConfig serverConfig = serversConfigMap.get(name);

            routes.route(route ->
                    route.path(serverConfig.getServiceUrn() + globalTailPathRegex)
                                    .filters(f -> f.rewritePath(serverConfig.getServiceUrn() + globalRegexTailPath, globalTailRegexReplacement))
                                    .uri(serverConfig.getFullUrn())
                                    .id(serverConfig.getServiceName())
            );
        });

        return routes.build();
    }
}
