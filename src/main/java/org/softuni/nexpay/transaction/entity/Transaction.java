package org.softuni.nexpay.transaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softuni.nexpay.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private User owner;
    @Column(nullable = false)
    private String sender;
    @Column(nullable = false)
    private String receiver;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private BigDecimal fee;
    @Column(nullable = false)
    private BigDecimal balanceLeft;
    @Column(nullable = false)
    private Currency currency;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @Column
    private String description;
    @Column
    private String failureReason;
    @Column(nullable = false)
    private LocalDateTime createdOn;
}
