package com.bntech.imperio.gateway.config;

import com.bntech.imperio.gateway.io.handler.ErrorHandler;
import com.bntech.imperio.gateway.io.handler.GatewayHandler;
import com.bntech.imperio.gateway.io.router.GatewayRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.server.RouterFunction;

import static com.bntech.imperio.gateway.config.Constants.APP_PROPERTIES;

@Configuration
@PropertySource(APP_PROPERTIES)
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
