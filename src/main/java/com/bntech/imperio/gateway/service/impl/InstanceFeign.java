package com.bntech.imperio.gateway.service.impl;

import com.bntech.imperio.gateway.service.FeignClient;
import com.bntech.imperio.gateway.service.InstanceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactivefeign.webclient.WebReactiveFeign;

import reactor.netty.http.client.HttpClient;

@Component
@Slf4j
public class InstanceFeign implements FeignClient<InstanceApi> {

    private final String hostname;
    private final String port;
    private final HttpClient tlsClient;


    @Autowired
    public InstanceFeign(@Value("${infrastructure.instances.host}") String hostname,
                         @Value("${infrastructure.instances.port}") String port,
                         HttpClient tlsClient
    ) {
        this.hostname = hostname;
        this.port = port;
        this.tlsClient = tlsClient;
    }

    public InstanceApi getClient() {
        return WebReactiveFeign.<InstanceApi>builder().httpClient(tlsClient).target(InstanceApi.class, hostname + ":" + port);
    }
}
