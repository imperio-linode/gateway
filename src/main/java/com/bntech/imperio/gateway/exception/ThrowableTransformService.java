package com.bntech.imperio.gateway.exception;

import org.springframework.http.HttpStatus;

/**
 * todo: This should is in common. Need to build it somehow into container.
 */
public interface ThrowableTransformService {
    String getMessage();
    HttpStatus getHttpStatus();
}
