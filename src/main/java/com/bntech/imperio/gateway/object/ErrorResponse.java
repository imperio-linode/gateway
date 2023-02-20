package com.bntech.imperio.gateway.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(String error) {

    @JsonCreator
    public ErrorResponse(@JsonProperty("error") final String error) {
        this.error = error;
    }
}
