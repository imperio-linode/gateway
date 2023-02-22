package com.bntech.imperio.gateway.feign;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import reactivefeign.webclient.WebReactiveFeign;

import reactor.netty.http.client.HttpClient;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

@Service
@Slf4j
public class FeignClient {

    private final String hostname;
    private final String port;
    private final HttpClient tlsClient;


    @Autowired
    public FeignClient(@Value("${infrastructure.instances.host}") String hostname,
                       @Value("${infrastructure.instances.port}") String port,
                       HttpClient tlsClient
    ) {
        this.hostname = hostname;
        this.port = port;
        this.tlsClient = tlsClient;
    }

    public InstanceApi getInstanceClient() {

        return WebReactiveFeign.<InstanceApi>builder().httpClient(tlsClient).target(InstanceApi.class, hostname + ":" + port);
    }
}
