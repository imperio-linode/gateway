package com.bntech.imperio.gateway.object;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonAutoDetect
@Getter
@Setter
@NoArgsConstructor
public class InstanceCreateRequest extends InstanceRequest<InstanceCreateRequest> {
    private String image;
    private String group;
    private List<String> authorizedKeys;
    private String rootPass;

    public InstanceCreateRequest(String type, String region, String label, String requestType, String image, String group, List<String> authorizedKeys, String rootPass) {
        super(type, region, label, requestType);
        this.image = image;
        this.group = group;
        this.authorizedKeys = authorizedKeys;
        this.rootPass = rootPass;
    }
}
