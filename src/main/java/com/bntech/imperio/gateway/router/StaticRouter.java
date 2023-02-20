package com.bntech.imperio.gateway.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.resources;

@Configuration
class StaticRouter {

    private static final String ROUTE = "/**";
    private static final String PUBLIC = "public/";

    @Bean
    static RouterFunction<?> doRoute() {
        return resources(ROUTE, new ClassPathResource(PUBLIC));
    }
}
