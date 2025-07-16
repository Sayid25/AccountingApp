package com.accountingapp.service.impl;

import com.accountingapp.dto.request.TransferMoneyRequest;
import com.accountingapp.dto.response.TransactionResponse;
import com.accountingapp.entity.Account;
import com.accountingapp.entity.Transaction;
import com.accountingapp.enumeration.TransactionStatus;
import com.accountingapp.repository.TransactionRepository;
import com.accountingapp.service.AccountingService;
import com.accountingapp.service.TransactionService;
import com.accountingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final AccountingService accountingService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<TransactionResponse>> getAll() {
        return ResponseEntity.ok(repository.findAll().stream().map(transaction -> modelMapper.map(transaction, TransactionResponse.class)).toList());
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> getAllByUserId(Long userId) {
        userService.findUserById(userId);
        return ResponseEntity.ok(
                repository.findByUserId(userId).stream().map(transaction ->
                        modelMapper.map(transaction, TransactionResponse.class)).toList()
        );
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> getAllByDate(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return ResponseEntity.ok(
                repository.findAllByCreatedAtBetween(dateFrom, dateTo).stream().map(transaction ->
                        modelMapper.map(transaction, TransactionResponse.class)).toList()
        );
    }

    @Override
    public ResponseEntity<TransactionResponse> transferMoney(TransferMoneyRequest request) {
        Account debitorAccount = accountingService.findAccountByNumber(request.getDebitAccountNumber());
        Account creditorAccount = accountingService.findAccountByNumber(request.getCreditAccountNumber());

        if (debitorAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Debit account balance is not enough");
        }
        debitorAccount.setBalance(debitorAccount.getBalance().subtract(request.getAmount()));
        creditorAccount.setBalance(creditorAccount.getBalance().add(request.getAmount()));
        accountingService.saveAll(List.of(debitorAccount, creditorAccount));

        Transaction transaction = repository.save(Transaction.builder()
                .debitAccount(debitorAccount)
                .creditAccount(creditorAccount)
                .amount(request.getAmount())
                .transactionStatus(TransactionStatus.SUCCESS)
                .description(request.getDescription())
                .build()
        );

        return ResponseEntity.ok(modelMapper.map(transaction, TransactionResponse.class));
    }
}
