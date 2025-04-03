package org.softuni.nexpay.wallet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softuni.nexpay.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String walletName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WalletStatus walletStatus;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private LocalDateTime updatedOn;
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;


}
