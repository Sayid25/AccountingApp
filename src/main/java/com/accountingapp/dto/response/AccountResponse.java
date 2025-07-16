package com.accountingapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountResponse {
    @JsonProperty("account_number")
    private String accountNumber;
}

