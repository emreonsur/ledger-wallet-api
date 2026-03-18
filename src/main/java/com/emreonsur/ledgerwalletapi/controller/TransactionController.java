package com.emreonsur.ledgerwalletapi.controller;

import com.emreonsur.ledgerwalletapi.dto.TransferRequest;
import com.emreonsur.ledgerwalletapi.entity.Transaction;
import com.emreonsur.ledgerwalletapi.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final WalletService walletService;

    public TransactionController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/api/transfers")
    public String transfer(@Valid @RequestBody TransferRequest request) {
        walletService.transfer(
                request.getSourceWalletId(),
                request.getDestinationWalletId(),
                request.getAmount(),
                request.getDescription()
        );
        return "Transfer completed successfully.";
    }

    @GetMapping("/api/transactions")
    public List<Transaction> getAllTransactions() {
        return walletService.getAllTransactions();
    }
}