package com.accountingapp.service.impl;

import com.accountingapp.entity.Account;
import com.accountingapp.entity.User;
import com.accountingapp.enumeration.AccountStatus;
import com.accountingapp.repository.AccountRepository;
import com.accountingapp.service.AccountingService;
import com.accountingapp.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountingServiceImpl implements AccountingService {
    private final AccountRepository repository;

    @Override
    public Account create(User user) {
        Account account = Account.builder()
                .accountNumber(Util.generateAccountNumber())
                .balance(BigDecimal.ZERO)
                .status(AccountStatus.ACTIVE)
                .user(user)
                .build();

        return repository.save(account);
    }

    public Account findAccountByNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber).orElseThrow(() ->
                new RuntimeException("Account not found {}"));
    }

    @Override
    public void saveAll(List<Account> list) {
        repository.saveAll(list);
    }
}
