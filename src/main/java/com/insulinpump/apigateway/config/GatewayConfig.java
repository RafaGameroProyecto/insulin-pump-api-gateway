package com.insulinpump.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Rutas para el servicio de pacientes
                .route("patient-service", r -> r
                        .path("/api/patients/**")
                        .uri("lb://PATIENT-SERVICE"))

                // Rutas para el servicio de dispositivos
                .route("device-service", r -> r
                        .path("/api/devices/**")
                        .uri("lb://DEVICE-SERVICE"))

                // Rutas para el servicio de lecturas
                .route("reading-service", r -> r
                        .path("/api/readings/**")
                        .uri("lb://READING-SERVICE"))

                .build();
    }
}
