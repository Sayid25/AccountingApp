package com.accountingapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class TokenResponse {
    private String login;

    @JsonProperty("access_token")
    private String accessToken;
}
