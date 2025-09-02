package com.apna.bank.dto;

import lombok.Data;

@Data
public class AccountDto {

    private String accountType;
    private double balance;
    private String userId;

    public AccountDto(String id, String savings, double v) {
    }
}
