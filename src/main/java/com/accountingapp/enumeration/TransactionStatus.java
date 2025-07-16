package com.accountingapp.enumeration;

public enum TransactionStatus {
    SUCCESS,
    FAILED;
    public String getTransactionStatus() {
        return this.name();
    }
}
