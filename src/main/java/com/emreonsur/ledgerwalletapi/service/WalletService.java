package com.emreonsur.ledgerwalletapi.service;

import com.emreonsur.ledgerwalletapi.entity.Transaction;
import com.emreonsur.ledgerwalletapi.entity.TransactionType;
import com.emreonsur.ledgerwalletapi.entity.Wallet;
import com.emreonsur.ledgerwalletapi.exception.InsufficientBalanceException;
import com.emreonsur.ledgerwalletapi.exception.WalletNotFoundException;
import com.emreonsur.ledgerwalletapi.repository.TransactionRepository;
import com.emreonsur.ledgerwalletapi.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public Wallet createWallet(String ownerName) {
        Wallet wallet = new Wallet();
        wallet.setOwnerName(ownerName);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setCreatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException(id));
    }

    @Transactional
    public Wallet deposit(Long walletId, BigDecimal amount, String description) {
        Wallet wallet = getWalletById(walletId);
        wallet.setBalance(wallet.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setDestinationWallet(wallet);
        transaction.setDescription(description);
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);
        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet withdraw(Long walletId, BigDecimal amount, String description) {
        Wallet wallet = getWalletById(walletId);

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setAmount(amount);
        transaction.setSourceWallet(wallet);
        transaction.setDescription(description);
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);
        return walletRepository.save(wallet);
    }

    @Transactional
    public void transfer(Long sourceWalletId, Long destinationWalletId, BigDecimal amount, String description) {
        Wallet sourceWallet = getWalletById(sourceWalletId);
        Wallet destinationWallet = getWalletById(destinationWalletId);

        if (sourceWallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }

        sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
        destinationWallet.setBalance(destinationWallet.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.TRANSFER);
        transaction.setAmount(amount);
        transaction.setSourceWallet(sourceWallet);
        transaction.setDestinationWallet(destinationWallet);
        transaction.setDescription(description);
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);
        walletRepository.save(sourceWallet);
        walletRepository.save(destinationWallet);
    }

    public List<Transaction> getWalletTransactions(Long walletId) {
        Wallet wallet = getWalletById(walletId);
        return transactionRepository.findBySourceWalletOrDestinationWalletOrderByCreatedAtDesc(wallet, wallet);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}