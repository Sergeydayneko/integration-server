package ru.dayneko.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Immutable;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ServerConfig {

    @Immutable
    @NotNull
    private String uri;

    @Immutable
    @NotNull
    private String port;

    @Immutable
    @NotNull
    private String servicePostfix;

    @Immutable
    @NotNull
    private String  serviceName;

    @Immutable
    @NotNull
    private String serviceUrn;

    @Nullable
    private String serviceDescription = "";

    public String getFullUrn() {
        return "http://" + uri + ":" + port + servicePostfix;
    }
}
