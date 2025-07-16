package com.accountingapp.controller;

import com.accountingapp.dto.request.TransferMoneyRequest;
import com.accountingapp.dto.response.TransactionResponse;
import com.accountingapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService service;

    @GetMapping("/get-all")
    public ResponseEntity<List<TransactionResponse>> getAll(){
        return service.getAll();
    }

    @GetMapping("/get-all/{userId}")
    public ResponseEntity<List<TransactionResponse>> getAllByUserId(@PathVariable Long userId){
        return service.getAllByUserId(userId);
    }

    @GetMapping("/get-all/by-date")
    public ResponseEntity<List<TransactionResponse>> getAllByDate(@RequestParam LocalDateTime dateFrom, @RequestParam LocalDateTime dateTo){
        return service.getAllByDate(dateFrom, dateTo);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transferMoney(@RequestBody TransferMoneyRequest request) {
        return service.transferMoney(request);
    }
}
