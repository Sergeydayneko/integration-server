package ru.dayneko.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String originalUri = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, "NO ORIGINAL URI FOUND");

        String route = Optional.ofNullable((Route) exchange.getAttribute(GATEWAY_ROUTE_ATTR))
                .filter(Objects::nonNull)
                .map(Route::getId)
                .orElse("NO ROUTE FOUND");

        String routeUri = Optional.ofNullable((String) exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR))
                        .filter(Objects::nonNull)
                        .orElse("NO ROUTE URI FOUND");

        log.info("Request {} is routed to {} ||| URI: {}", originalUri, route, routeUri);

        return chain.filter(exchange);
    }
}
