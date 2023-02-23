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
    private List<String> authorized_keys;
    private String root_pass;

    public InstanceCreateRequest(String type, String region, String label, String image, String group, List<String> authorized_keys, String root_pass) {
        super(type, region, label);
        this.image = image;
        this.group = group;
        this.authorized_keys = authorized_keys;
        this.root_pass = root_pass;
    }
}
