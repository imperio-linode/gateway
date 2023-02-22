package com.bntech.imperio.gateway.handler;


import com.bntech.imperio.gateway.feign.FeignClient;
import com.bntech.imperio.gateway.feign.InstanceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class GatewayHandler {

    private final InstanceApi instances;

    @Autowired
    public GatewayHandler(FeignClient client) {
        this.instances = client.getInstanceClient();
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("Hello"));
    }

    public Mono<ServerResponse> instanceDetails(ServerRequest request) {
        log.info("Received request to perform feign call");
        return instances.getInstanceDetails(request.pathVariable("id"));
    }

    public Mono<ServerResponse> addInstance(ServerRequest request) {
        return instances.getInstanceDetails(request.pathVariable("id"));
    }
}
