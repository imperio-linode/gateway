package com.bntech.imperio.gateway.service.impl;

import com.bntech.imperio.gateway.object.InstanceCreateRequest;
import com.bntech.imperio.gateway.object.InstanceCreateResponse;
import com.bntech.imperio.gateway.service.Requests;
import com.bntech.imperio.gateway.service.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import static com.bntech.imperio.gateway.config.Constants.api_ID;
import static com.bntech.imperio.gateway.config.Constants.param_ID;
import static com.bntech.imperio.gateway.service.Util.paramToWildcard;

@Component
@Slf4j
public class RequestsImpl implements Requests {

    private final HttpClient instancesClient;

    @Autowired
    public RequestsImpl(HttpClient tlsClient, @Value("${infrastructure.instances.host}") String instancesHost) {
        this.instancesClient = tlsClient.baseUrl(instancesHost);
    }

    public Mono<ServerResponse> getInstanceDetails(String id) {
        return instancesClient.get()
                .uri(api_ID.replace(paramToWildcard(param_ID), id))
                .responseSingle((res, buf) -> Util
                        .stringToServerResponse(buf.asString()))
                .log("service.impl.RequestsImpl.getInstanceDetails");
    }

    public Mono<ServerResponse> createInstance(InstanceCreateRequest instanceCreateRequest) {
        log.info("Creating with: {}", instanceCreateRequest.getType());


        return Mono.just(new InstanceCreateResponse())
                .map(InstanceCreateResponse::toString)
                .log()
                .transform(Util::stringToServerResponse);
    }

}
