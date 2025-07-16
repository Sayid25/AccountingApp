package com.accountingapp.repository;

import com.accountingapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("""
              SELECT DISTINCT t
              FROM Transaction t
              WHERE t.debitAccount.user.id = :userId OR t.creditAccount.user.id = :userId
          """)
    List<Transaction> findByUserId(Long userId);

    List<Transaction> findAllByCreatedAtBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
}
