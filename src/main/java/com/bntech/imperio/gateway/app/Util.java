package com.bntech.imperio.gateway.app;

import com.bntech.imperio.gateway.app.object.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public class Util {
    static ObjectMapper mapper;

    public static String toPathVariable(String variable) {
        return "{" + variable + "}";
    }

    public static Mono<ServerResponse> stringServerResponse(Mono<String> instanceDetails) {
        return instanceDetails
                .flatMap(userResponse -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(userResponse), String.class));
    }

    public static Mono<ServerResponse> stringServerResponse(String responseBody, HttpStatus status) {
        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(responseBody));
    }

    public static Mono<ServerResponse> stringServerResponse(Tuple2<HttpResponseStatus, String> responseData) {
        HttpStatus status = HttpStatus.valueOf(responseData.getT1().code());
        String body = responseData.getT2();
        return stringServerResponse(body, status);
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
