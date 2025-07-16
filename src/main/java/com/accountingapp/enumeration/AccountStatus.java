package com.accountingapp.enumeration;

public enum AccountStatus {
    ACTIVE,
    CLOSED,
    BLOCKED;
    public String getAccountStatus() {
        return this.name();
    }
}
