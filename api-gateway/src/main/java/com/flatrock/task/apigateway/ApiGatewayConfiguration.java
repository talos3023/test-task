package com.flatrock.task.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route
                        .path("/orders/**")
                        .uri("lb://package-and-delivery-service"))
                .route(route -> route
                        .path("/categories/**")
                        .uri("lb://products-service"))
                .route(route -> route
                        .path("/products/**")
                        .uri("lb://products-service"))
                .build();
    }
}
