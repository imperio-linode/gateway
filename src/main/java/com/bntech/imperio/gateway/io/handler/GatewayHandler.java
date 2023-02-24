package com.bntech.imperio.gateway.io.handler;


import com.bntech.imperio.gateway.object.InstanceCreateRequest;
import com.bntech.imperio.gateway.app.FeignClient;
import com.bntech.imperio.gateway.app.Requests;
import com.bntech.imperio.gateway.app.InstanceApi;
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

    //todo: Temporary replaced with http call due to https://github.com/PlaytikaOSS/feign-reactive/pull/539. Revert when done.
//    private final InstanceApi instances;
    private final Requests http;

    @Autowired
    public GatewayHandler(FeignClient<InstanceApi> client, Requests http) {
//        this.instances = client.getClient();
        this.http = http;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("Hello"));
    }

    public Mono<ServerResponse> instanceDetailsRequest(ServerRequest request) {
        log.info("Received request to perform feign call");
        return http.getInstanceDetails(request.pathVariable("id"));
//        return instances.getInstanceDetails(request.pathVariable("id"));
    }

    public Mono<ServerResponse> instanceCreateRequest(ServerRequest request) {
        return request
                .bodyToMono(InstanceCreateRequest.class)
                .log("io.handler.GatewayHandler.addInstance")
                .transform(http::createInstance);
    }

}
