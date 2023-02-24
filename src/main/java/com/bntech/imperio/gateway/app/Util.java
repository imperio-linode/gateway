package com.bntech.imperio.gateway.app;

import com.bntech.imperio.gateway.object.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class Util {
    static ObjectMapper mapper;

    public static String paramToWildcard(String param) {
        return "{" + param + "}";
    }

    public static Mono<ServerResponse> stringServerResponse(Mono<String> instanceDetails) {
        return instanceDetails
                .log("io.handler.GatewayHandler.stringToServerResponse")
                .flatMap(userResponse -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(userResponse), String.class));
    }

    public static <T> Mono<String> toJsonString(Mono<T> obj) {
        return obj.map(o -> {
            try {
                return mapper.writeValueAsString(o);
            } catch (JsonProcessingException e) {
                return new ErrorResponse("Can't map").error();
            }
        });
    }
}
