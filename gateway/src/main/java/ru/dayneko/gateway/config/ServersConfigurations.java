package ru.dayneko.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.dayneko.gateway.exception.BeanDefinitionException;
import ru.dayneko.gateway.model.ServerConfig;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Configuration
@Slf4j
public class ServersConfigurations {

    @Bean
    ServerConfig storeServiceApp(
            @Value("${gateway.servers.store.url}") String url,
            @Value("${gateway.servers.store.port}") String port,
            @Value("${gateway.servers.store.postfix}") String postfix,
            @Value("${gateway.servers.store.name}") String serviceName,
            @Value("${gateway.servers.store.desc}") String desc,
            @Value("${gateway.intercept-urls.store}") String urn) {

        checkServiceConfigForNotNullable(url, port, postfix, serviceName, urn);

        return new ServerConfig(url, port, postfix, serviceName, urn, desc);
    }

    @Bean
    ServerConfig productServiceApp(
            @Value("${gateway.servers.product.url}") String url,
            @Value("${gateway.servers.product.port}") String port,
            @Value("${gateway.servers.product.postfix}") String postfix,
            @Value("${gateway.servers.product.name}") String serviceName,
            @Value("${gateway.servers.product.desc}") String desc,
            @Value("${gateway.intercept-urls.product}") String urn) {

        checkServiceConfigForNotNullable(url, port, postfix, serviceName, urn);

        return new ServerConfig(url, port, postfix, serviceName, urn, desc);
    }

    @Bean
    @DependsOn({"storeServiceApp", "productServiceApp"})
    public Set<String> serversList(@NotNull @NotEmpty List<ServerConfig> configs) {
        log.debug("Configuring list of services names");

        return configs.stream().map(ServerConfig::getServiceName).collect(toSet());
    }

    private void checkServiceConfigForNotNullable(String url, String port, String postfix, String serviceName, String urn) {
        log.debug("Checking {} for nullable args. Url: {} ||| Port: {} ||| Urn: ", serviceName, url, port, urn);

        if ((null == url) || (null == port) || (null == postfix) || (null == serviceName) || (null == urn)) {
            throw new BeanDefinitionException(serviceName);
        }
    }

}
