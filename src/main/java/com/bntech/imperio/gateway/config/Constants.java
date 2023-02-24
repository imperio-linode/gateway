package com.bntech.imperio.gateway.config;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * Application constants.
 * todo: This should is in common. Need to build it somehow into container.
 */
@NoArgsConstructor
public final class Constants {

    public static final String APP_PROPERTIES = "classpath:application.yml";

    public static final String api_HELLO = "/hello";
    public static final String api_ID = "/one/{id}";
    public static final String api_ADD = "/add";
    public static final String api_INSTANCE = "/instances";

    public static final String param_ID = "id";

}
