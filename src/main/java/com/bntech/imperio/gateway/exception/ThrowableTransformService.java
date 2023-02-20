package com.bntech.imperio.gateway.exception;

import org.springframework.http.HttpStatus;

public interface ThrowableTransformService {
    String getMessage();
    HttpStatus getHttpStatus();
}
