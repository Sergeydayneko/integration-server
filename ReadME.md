Ancillary service with service logic.
Except for the server config, it can be used as an Ambassador pattern.

It consists of three parts:

1. Spring cloud config for distributed configs that are needed in several services
https://cloud.spring.io/spring-cloud-config/reference/html/

2. Spring Cloud Gateway. It is a proxy service and serves as a single entry point to the application. Also, it was added to it a resiliencefoggy circus breaker to ensure fault tolerance and a resiliencefoggy raylimter to ensure a constant maximum load
https://spring.io/projects/spring-cloud-gateway
https://github.com/resilience4j/resilience4j

3. Server part for eureka
https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html