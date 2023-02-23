package com.bntech.imperio.gateway.service;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class Util {

    public static String paramToWildcard(String param) {
        return "{" + param + "}";
    }

    public static Mono<ServerResponse> stringToServerResponse(Mono<String> instanceDetails) {
        return instanceDetails
                .log("io.handler.GatewayHandler.stringToServerResponse")
                .flatMap(userResponse -> ServerResponse.ok().body(Mono.just(userResponse), String.class));
    }
}
