package com.accountingapp.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMoneyRequest {
    private String debitAccountNumber;

    private String creditAccountNumber;

    private BigDecimal amount;

    private String description;
}
