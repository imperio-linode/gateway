package com.bntech.imperio.gateway.io.router;


import com.bntech.imperio.gateway.io.handler.ErrorHandler;
import com.bntech.imperio.gateway.io.handler.GatewayHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static com.bntech.imperio.gateway.config.Constants.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GatewayRouter {

    @Bean
    public static RouterFunction<?> doRoute(final GatewayHandler gatewayHandler, final ErrorHandler errorHandler) {
        return
                nest(path(api_INSTANCE),
                        route(GET(api_ID),
                                gatewayHandler::instanceDetails
                        ).andRoute(POST(api_ADD),
                                gatewayHandler::addInstance)
                ).andOther(StaticRouter.doRoute()
                ).andOther(route(GET(api_HELLO), gatewayHandler::hello)
                );
    }
}
