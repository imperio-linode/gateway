package com.bntech.imperio.gateway;

import io.cloudevents.core.provider.EventFormatProvider;
import io.cloudevents.jackson.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableWebFlux
public class GatewayApplication {

    public static void main(String[] args) {
        EventFormatProvider.getInstance().registerFormat(new JsonFormat());
        SpringApplication.run(GatewayApplication.class, args);
    }

}
