package com.accountingapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserUpdateRequest {
    private String username;

    @JsonProperty("full_name")
    private String fullName;
}
