package com.emreonsur.ledgerwalletapi.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {
        super("Insufficient balance for this operation.");
    }
}