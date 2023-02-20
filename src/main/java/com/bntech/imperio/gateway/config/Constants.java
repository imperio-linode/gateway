package com.bntech.imperio.gateway.config;

import lombok.NoArgsConstructor;

/**
 * Application constants.
 */
@NoArgsConstructor
public final class Constants {

    public static final String SYSTEM = "system";
    public static final String PROFILE_DEV = "dev";
    public static final String POLICY_DIRECTIVE = "default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:";
    public static final String BOOTSTRAP_SERVERS = "localhost";
    public static final String BOOTSTRAP_PORT = "9192";

    public static final String api_USER = "/u";
    public static final String api_HELLO = "/hello";
    public static final String api_ID = "/one/{id}";
    public static final String api_ADD = "/add";
    public static final String api_INSTANCE = "/i";





}
