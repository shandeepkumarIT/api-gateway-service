package com.dreamlayer.api.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		
        return builder.routes()
                .route("product-service", (r) -> r.path("/api/product").uri("lb://product-service"))
                .route("order-service", (r) -> r.path("/api/order").uri("lb://order-service"))
                .route("inventory-service", (r) -> r.path("/api/inventory").uri("lb://inventory-service"))
                .route("discover-service", (r) -> r.path("/eureka/web").filters(a -> a.setPath("/")).uri("http://localhost:8761"))
                .route("discover-service-static", (r) -> r.path("/eureka/**").uri("http://localhost:8761"))
                .build();
    }
}
