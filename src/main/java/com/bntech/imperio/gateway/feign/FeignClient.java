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
    private final String keyStoreLocation;
    private final String keyStorePassword;
    private final String keyStoreType;
    private final String trustStoreLocation;
    private final String trustStorePassword;

    @Autowired
    public FeignClient(@Value("${infrastructure.instances.host}") String hostname,
                       @Value("${infrastructure.instances.port}") String port,
                       @Value("${infrastructure.tls.key-store}")String keyStoreLocation,
                       @Value("${infrastructure.tls.key-store-password}")String keyStorePassword,
                       @Value("${infrastructure.tls.key-store-type}")String keyStoreType,
                       @Value("${infrastructure.tls.ca}")String trustStoreLocation,
                       @Value("${infrastructure.tls.ca-password}")String trustStorePassword
    ) {
        this.hostname = hostname;
        this.port = port;
        this.keyStoreLocation = keyStoreLocation;
        this.keyStorePassword = keyStorePassword;
        this.keyStoreType = keyStoreType;
        this.trustStoreLocation = trustStoreLocation;
        this.trustStorePassword = trustStorePassword;
    }

    public InstanceApi getInstanceClient() {
        HttpClient httpClient = HttpClient.create().secure(spec -> {
            try {
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(new FileInputStream(ResourceUtils.getFile(keyStoreLocation)), keyStorePassword.toCharArray());
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

                KeyStore trustStore = KeyStore.getInstance(keyStoreType);
                trustStore.load(new FileInputStream((ResourceUtils.getFile(trustStoreLocation))), trustStorePassword.toCharArray());
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(trustStore);

                SslContext context = SslContextBuilder.forClient()
                        .keyManager(keyManagerFactory)
                        .trustManager(trustManagerFactory)
                        .build();

                spec.sslContext(context);
            } catch (Exception e) {
               log.warn("Unable to set SSL Context", e);
            }
        });

        return WebReactiveFeign.<InstanceApi>builder().httpClient(httpClient).target(InstanceApi.class, hostname + ":" + port);
    }
}
