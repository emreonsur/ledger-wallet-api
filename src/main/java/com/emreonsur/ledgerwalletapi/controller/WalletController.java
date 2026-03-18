package com.emreonsur.ledgerwalletapi.controller;

import com.emreonsur.ledgerwalletapi.dto.AmountRequest;
import com.emreonsur.ledgerwalletapi.dto.CreateWalletRequest;
import com.emreonsur.ledgerwalletapi.entity.Transaction;
import com.emreonsur.ledgerwalletapi.entity.Wallet;
import com.emreonsur.ledgerwalletapi.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public Wallet createWallet(@Valid @RequestBody CreateWalletRequest request) {
        return walletService.createWallet(request.getOwnerName());
    }

    @GetMapping
    public List<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable Long id) {
        return walletService.getWalletById(id);
    }

    @PostMapping("/{id}/deposit")
    public Wallet deposit(@PathVariable Long id, @Valid @RequestBody AmountRequest request) {
        return walletService.deposit(id, request.getAmount(), request.getDescription());
    }

    @PostMapping("/{id}/withdraw")
    public Wallet withdraw(@PathVariable Long id, @Valid @RequestBody AmountRequest request) {
        return walletService.withdraw(id, request.getAmount(), request.getDescription());
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getWalletTransactions(@PathVariable Long id) {
        return walletService.getWalletTransactions(id);
    }
}