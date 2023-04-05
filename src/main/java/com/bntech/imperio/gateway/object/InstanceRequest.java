package com.bntech.imperio.gateway.object;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonAutoDetect
@Getter
@Setter
@NoArgsConstructor
public class InstanceRequest<T> {
    private String type;
    private String region;
    private String label;
    @JsonAlias("request_type")
    private String requestType;

    public InstanceRequest(String type, String region, String label, String requestType) {
        this.type = type;
        this.region = region;
        this.label = label;
        this.requestType = requestType;
    }
}
