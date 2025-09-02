package com.apna.bank.Service;

import com.apna.bank.Entity.Account;
import com.apna.bank.Entity.Transaction;
import com.apna.bank.dto.AccountDto;
import com.apna.bank.dto.TransferRequest;

import java.util.List;

public interface AccountService {
    Account createAccount(AccountDto accountDto);
    double getBalance(String accountNumber);
    Account deposit(String accountNumber, double amount);
    Account withdraw(String accountNumber, double amount);

    String transferMoney(TransferRequest transferRequest);
    List<Transaction> getMonthlyStatement(String accountNumber, int year, int mouth);
    double calculateInterest(String accountNumber,double rate, int months);




}
