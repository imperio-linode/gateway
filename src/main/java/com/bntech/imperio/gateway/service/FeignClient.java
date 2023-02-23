package com.bntech.imperio.gateway.service;

import org.springframework.stereotype.Service;

@Service
public interface FeignClient<T> {
    T getClient();
}
