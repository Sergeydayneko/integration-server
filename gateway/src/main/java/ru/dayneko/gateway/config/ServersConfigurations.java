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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class ServersConfigurations {

    @Bean
    ServerConfig initStoreService(
            @Value("${gateway.servers.store.url}") String url,
            @Value("${gateway.servers.store.port}") String port,
            @Value("${gateway.servers.store.name}") String serviceName,
            @Value("${gateway.servers.store.desc}") String desc) {

        checkServiceConfigForNotNullable(url, port, serviceName);

        return new ServerConfig(url, port, serviceName, desc);
    }

    @Bean
    ServerConfig initProductService(
            @Value("${gateway.servers.product.url}") String url,
            @Value("${gateway.servers.product.port}") String port,
            @Value("${gateway.servers.product.name}") String serviceName,
            @Value("${gateway.servers.product.desc}") String desc) {

        checkServiceConfigForNotNullable(url, port, serviceName);

        return new ServerConfig(url, port, serviceName, desc);
    }

    @Bean
    @DependsOn({"initStoreService", "initProductService"})
    public Map<String, ServerConfig> initConfigMapBean(@NotNull @NotEmpty List<ServerConfig> configs) {
        log.debug("Configs of servers are: {}", configs);

        return configs.stream().collect(Collectors.toMap(ServerConfig::getServiceName, Function.identity()));
    }

    private void checkServiceConfigForNotNullable(String url, String port, String serviceName) {
        log.debug("Checking {} for nullable args. Url: {} ||| Port: {}", serviceName, url, port);

        if ((null == url) || (null == port) || (null == serviceName)) {
            throw new BeanDefinitionException(serviceName);
        }

    }

}
