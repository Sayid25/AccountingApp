package com.accountingapp.dto.response;

import com.accountingapp.enumeration.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Long id;

    @JsonProperty("debit_account")
    private AccountResponse debitAccount;

    @JsonProperty("credit_account")
    private AccountResponse creditAccount;

    @JsonProperty("transaction_status")
    private TransactionStatus transactionStatus;

    private String description;

    private BigDecimal amount;

    @JsonProperty("transaction_date_time")
    private LocalDateTime createdAt;
}
