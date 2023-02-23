package com.bntech.imperio.gateway.feign;

import feign.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import static com.bntech.imperio.gateway.config.Constants.api_ID;

@Component
@Slf4j
public class FeignFallbackApi {

    private final HttpClient instancesClient;

    @Autowired
    public FeignFallbackApi(HttpClient tlsClient, @Value("${infrastructure.instances.host}") String instancesHost) {
        this.instancesClient = tlsClient.baseUrl(instancesHost);
    }

    public Mono<ServerResponse> getInstanceDetails(@Param("id") String id) {
        return instancesClient.get()
                .uri(api_ID.replace("{id}", id))
                .responseSingle((res, buf) -> this.serverResponse(buf.asString())).log("com.bntech.userhandler.getDetails");
    }

    Mono<ServerResponse> serverResponse(Mono<String> instanceDetails) {
        return instanceDetails
                .log("com.bntech.userhandler.ServerResponse")
                .flatMap(userResponse -> ServerResponse.ok().body(Mono.just(userResponse), String.class));
    }
}
