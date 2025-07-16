package com.accountingapp.service;

import com.accountingapp.dto.request.TransferMoneyRequest;
import com.accountingapp.dto.response.TransactionResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    ResponseEntity<List<TransactionResponse>> getAll();

    ResponseEntity<List<TransactionResponse>> getAllByUserId(Long userId);

    ResponseEntity<List<TransactionResponse>> getAllByDate(LocalDateTime dateFrom, LocalDateTime dateTo);

    ResponseEntity<TransactionResponse> transferMoney(TransferMoneyRequest request);
}
