package com.bntech.imperio.gateway.app;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.bntech.imperio.gateway.config.Constants.api_ID;
import static com.bntech.imperio.gateway.config.Constants.api_ADD;

@Service
@Headers({ "Accept: application/json" })
public interface InstanceApi {

    @RequestLine("GET " + api_ID)
    Mono<ServerResponse> getInstanceDetails(@Param("id") Mono<String> id);

    @RequestLine("POST " + api_ADD)
    Mono<ServerResponse> addInstance();

}
