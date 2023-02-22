package com.bntech.imperio.gateway.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactivefeign.webclient.WebReactiveFeign;

@Service
public class FeignClient {

    private final String hostname;
    private final String port;

    @Autowired
    public FeignClient(@Value("${infrastructure.instances.host}") String hostname, @Value("${infrastructure.instances.port}") String port) {
        this.hostname = hostname;
        this.port = port;
    }

    public InstanceApi getInstanceClient() {
        return WebReactiveFeign.<InstanceApi>builder().target(InstanceApi.class, hostname + ":" + port);
    }
}
