package com.apna.bank.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private String fromAccount;
    private String toAccount;
    private double amount;
}
