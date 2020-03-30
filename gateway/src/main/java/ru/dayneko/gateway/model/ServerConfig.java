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
public class ServerConfigq {

    @Immutable
    @NotNull
    private String url;

    @Immutable
    @NotNull
    private String port;

    @Immutable
    @NotNull
    private String  serviceName;

    @Nullable
    private String serviceDescription;
}
