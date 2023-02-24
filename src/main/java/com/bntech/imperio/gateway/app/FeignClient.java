package com.bntech.imperio.gateway.app;

import org.springframework.stereotype.Service;

@Service
public interface FeignClient<T> {
    T getClient();
}
