package com.bntech.imperio.gateway.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * todo: This should is in common. Need to build it somehow into container.
 */
public record ErrorResponse(String error) {

    @JsonCreator
    public ErrorResponse(@JsonProperty("error") final String error) {
        this.error = error;
    }
}
