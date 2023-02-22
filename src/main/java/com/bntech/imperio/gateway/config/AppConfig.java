package com.bntech.imperio.gateway.config;

import com.bntech.imperio.gateway.handler.ErrorHandler;
import com.bntech.imperio.gateway.handler.GatewayHandler;
import com.bntech.imperio.gateway.router.GatewayRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {

    @Bean
    ErrorHandler errorHandler() {
        return new ErrorHandler();
    }

    @Bean
    RouterFunction<?> mainRouterFunction(final GatewayHandler gatewayHandler, final ErrorHandler errorHandler) {
        return GatewayRouter.doRoute(gatewayHandler, errorHandler);
    }
}
