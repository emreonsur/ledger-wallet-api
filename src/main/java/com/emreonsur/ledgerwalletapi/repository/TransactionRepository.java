package com.emreonsur.ledgerwalletapi.repository;

import com.emreonsur.ledgerwalletapi.entity.Transaction;
import com.emreonsur.ledgerwalletapi.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySourceWalletOrDestinationWalletOrderByCreatedAtDesc(Wallet sourceWallet, Wallet destinationWallet);
}