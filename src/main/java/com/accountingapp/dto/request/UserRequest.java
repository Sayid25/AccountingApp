package com.accountingapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @JsonProperty("full_name")
    @NotNull
    private String fullName;
}
