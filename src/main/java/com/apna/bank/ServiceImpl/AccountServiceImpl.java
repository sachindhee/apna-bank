package com.apna.bank.ServiceImpl;

import com.apna.bank.Entity.Account;
import com.apna.bank.Entity.Transaction;
import com.apna.bank.Service.AccountService;
import com.apna.bank.dto.AccountDto;
import com.apna.bank.dto.TransferRequest;
import com.apna.bank.repository.AccountRepository;
import com.apna.bank.repository.TransctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransctionRepository transctionRepository;

    @Override
    public Account createAccount(AccountDto accountDto) {

        String accountNumber = "AC" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountType(accountDto.getAccountType())
                .balance(accountDto.getBalance())
                .userId(accountDto.getUserId())
                .createdAt(LocalDateTime.now())
                .build();

        return accountRepository.save(account);
    }

    @Override
    public double getBalance(String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));


        return account.getBalance();
    }

    @Override
    public Account deposit(String accountNumber, double amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found !"));
               account.setBalance(account.getBalance()+amount);
        Account updatedAccount = accountRepository.save(account);
        transctionRepository.save(Transaction.builder()
                .accountNumber(accountNumber)
                .type("DEPOSIT")
                        .amount(amount)
                .description("Amount deposited")
                .localDateTime(LocalDateTime.now())
                .build());
        return updatedAccount;
    }

    @Override
    public Account withdraw(String accountNumber, double amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found !"));
        if (account.getBalance() < amount)
        {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance()-amount);


        Account save = accountRepository.save(account);

        transctionRepository.save(Transaction.builder()
                .accountNumber(accountNumber)
                .type("WITHDRAW")
                .amount(amount)
                .description("Amount withdrawn")
                .localDateTime(LocalDateTime.now())
                .build());
        return save;
    }

    @Override
    public String transferMoney(TransferRequest transferRequest) {

        Account fromAcc = accountRepository.findByAccountNumber(transferRequest.getFromAccount())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));
        Account toAcc = accountRepository.findByAccountNumber(transferRequest.getToAccount())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (fromAcc.getBalance() < transferRequest.getAmount())
        {
            throw new RuntimeException("Insufficient balance in sender account");
        }

        fromAcc.setBalance(fromAcc.getBalance()-transferRequest.getAmount());
        toAcc.setBalance(toAcc.getBalance()+transferRequest.getAmount());
        accountRepository.save(fromAcc);
        accountRepository.save(toAcc);

        transctionRepository.save(Transaction.builder()
                .accountNumber(fromAcc.getAccountNumber())
                .type("Transfer")
                .amount(transferRequest.getAmount())
                .description("Sent to "+toAcc.getAccountNumber())
                .localDateTime(LocalDateTime.now())
                .build()
        );

        transctionRepository.save(Transaction.builder()
                .accountNumber(toAcc.getAccountNumber())
                .type("TRANSFER")
                .amount(transferRequest.getAmount())
                .description("Received from "+fromAcc.getAccountNumber())
                .localDateTime(LocalDateTime.now())
                .build()
        );


        return "Transfer successfull from "+fromAcc.getAccountNumber()+"to "+toAcc.getAccountNumber();
    }

    @Override
    public List<Transaction> getMonthlyStatement(String accountNumber, int year, int mouth) {
        List<Transaction> allTransaction = transctionRepository.findByAccountNumber(accountNumber);

        return allTransaction.stream().filter(t->t.getLocalDateTime().getYear() == year && t.getLocalDateTime().getMonthValue()==mouth).collect(Collectors.toList());
    }

    @Override
    public double calculateInterest(String accountNumber, double rate, int months) {

        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account not found !"));
        double balance = account.getBalance();
        double timeInYear = months/12.0;



        return  ( balance * rate * timeInYear ) / 10;
    }



    public List<Account> getAll()
    {
        return accountRepository.findAll();
    }
}
