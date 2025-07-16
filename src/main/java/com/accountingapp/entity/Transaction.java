package com.accountingapp.entity;

import com.accountingapp.abstraction.AuditableEntity;
import com.accountingapp.enumeration.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "accounting_schema", name = "transaction")
public class Transaction extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Account debitAccount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "credit_account_id")
    private Account creditAccount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private String description;

    private BigDecimal amount;
}

