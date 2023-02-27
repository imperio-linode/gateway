package com.bntech.imperio.gateway.app.impl;

import com.bntech.imperio.gateway.object.InstanceCreateRequest;
import com.bntech.imperio.gateway.app.Requests;
import com.bntech.imperio.gateway.app.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import reactor.netty.http.client.HttpClient;

import static com.bntech.imperio.gateway.config.Constants.*;
import static com.bntech.imperio.gateway.app.Util.paramToWildcard;
import static io.netty.util.CharsetUtil.US_ASCII;

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
                        .stringServerResponse(buf.asString()))
                .log("service.impl.RequestsImpl.getInstanceDetails");
    }

    public Mono<ServerResponse> createInstance(Mono<InstanceCreateRequest> request) {
        return request.flatMap(instance -> {
            ObjectMapper mapper = new ObjectMapper();
            ByteBuf requestBody;

            try {
                requestBody = Unpooled.wrappedBuffer(mapper.writeValueAsBytes(instance));
                log.info("createInstance: " + requestBody.toString(US_ASCII));
            } catch (JsonProcessingException e) {
                return Mono.error(new ServerWebInputException("Error serializing request body."));
            }

            return instancesClient.post()
                    .uri(api_ADD)
                    .send(Mono.just(requestBody))
                    .responseSingle((res, buf) -> Util.stringServerResponse(buf.asString()))
                    .log("createInstance.post-request.log")
                    .onErrorResume(ex -> {
                        if (ex instanceof ServerWebInputException) {
                            ServerWebInputException swie = (ServerWebInputException) ex;
                            return ServerResponse.badRequest().body(BodyInserters.fromValue(swie.getMessage()));
                        } else {
                            // Handle other exceptions as needed
                            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(BodyInserters.fromValue("An error occurred while processing the instance create request."));
                        }
                    });
        });
    }
}
