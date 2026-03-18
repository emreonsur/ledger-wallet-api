package com.emreonsur.ledgerwalletapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateWalletRequest {

    @NotBlank(message = "Owner name is required.")
    @Size(max = 100, message = "Owner name must be at most 100 characters.")
    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}