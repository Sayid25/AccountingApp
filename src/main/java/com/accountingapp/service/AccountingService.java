package com.accountingapp.service;

import com.accountingapp.entity.Account;
import com.accountingapp.entity.User;

import java.util.List;

public interface AccountingService {
    Account create(User user);

    Account findAccountByNumber(String accountNumber);

    void saveAll(List<Account> list);
}
