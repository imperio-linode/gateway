package com.bntech.imperio.gateway.service;

import com.bntech.imperio.gateway.object.InstanceCreateRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface Requests {

    Mono<ServerResponse> getInstanceDetails(String id);
    Mono<ServerResponse> createInstance(InstanceCreateRequest request);
}
