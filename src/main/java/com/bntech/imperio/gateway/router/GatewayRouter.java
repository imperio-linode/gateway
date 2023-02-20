package com.bntech.imperio.gateway.router;


import com.bntech.imperio.gateway.handler.ErrorHandler;
import com.bntech.imperio.gateway.handler.InstanceHandler;
import com.bntech.imperio.gateway.handler.UserHandler;
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
    public static RouterFunction<?> doRoute(final UserHandler userHandler, final InstanceHandler instanceHandler, final ErrorHandler errorHandler) {
        return
                nest(path(api_INSTANCE),
                        route(GET(api_ID),
                                instanceHandler::instanceDetails
                        ).andRoute(POST(api_ADD),
                                instanceHandler::addInstance)
                ).andOther(StaticRouter.doRoute()
                ).andOther(route(GET(api_HELLO), instanceHandler::hello)
                );
    }
}
