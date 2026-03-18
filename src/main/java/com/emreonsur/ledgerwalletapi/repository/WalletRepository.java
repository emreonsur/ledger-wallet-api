package com.emreonsur.ledgerwalletapi.repository;

import com.emreonsur.ledgerwalletapi.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}