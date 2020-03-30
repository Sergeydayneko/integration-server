package ru.dayneko.gateway.exception;

public class BeanDefinitionException extends IllegalStateException {

    public BeanDefinitionException(String serviceConfigName) {
        super(String.format("There is incorrect data for %s service. Check application file and config map configuration", serviceConfigName));
    }
}
