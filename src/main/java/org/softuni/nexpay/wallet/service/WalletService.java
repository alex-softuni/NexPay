package org.softuni.nexpay.wallet.service;

import org.softuni.nexpay.user.entity.User;
import org.softuni.nexpay.wallet.entity.Wallet;
import org.softuni.nexpay.wallet.entity.WalletStatus;
import org.softuni.nexpay.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createNewWallet(User user) {

        return walletRepository.save(Wallet.builder()
                .walletName("Wallet")
                .walletStatus(WalletStatus.ACTIVE)
                .balance(BigDecimal.valueOf(100.00))
                .currency(Currency.getInstance("EUR"))
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .owner(user)
                .build());

    }
}
