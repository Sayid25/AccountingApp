package com.accountingapp.entity;

import com.accountingapp.abstraction.AuditableEntity;
import com.accountingapp.enumeration.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "accounting_schema", name = "account")
public class Account extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "debitAccount", cascade = CascadeType.ALL)
    private List<Transaction> debitTransactions;

    @OneToMany(mappedBy = "creditAccount", cascade = CascadeType.ALL)
    private List<Transaction> creditTransactions;
}
