package com.bntech.imperio.gateway.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InstanceRequestType {
    regular("regular"),
    kubernetesHost("kubernetes-host"),
    kubernetesWorker("kubernetes-worker");

    private final String InstanceRequestType;
}
