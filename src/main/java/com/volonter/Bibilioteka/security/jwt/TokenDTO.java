package com.volonter.Bibilioteka.security.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDTO {
    @JsonProperty("token")
    private String value;

    @JsonCreator
    public TokenDTO(@JsonProperty("token") String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
