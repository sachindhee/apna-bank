package com.apna.bank.Controller;

import com.apna.bank.Entity.Account;
import com.apna.bank.Entity.Transaction;
import com.apna.bank.ServiceImpl.AccountServiceImpl;
import com.apna.bank.dto.AccountDto;
import com.apna.bank.dto.TransferRequest;
import com.apna.bank.repository.TransctionRepository;
import com.apna.bank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserRepository userRepository;
    private final AccountServiceImpl accountService;
    private final TransctionRepository transctionRepository;

    @PostMapping
   public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto)
   {
       Account account  = accountService.createAccount(accountDto);
      return ResponseEntity.ok(account);
   }
   @GetMapping("/all-account")
   public List<Account> getAll()
   {
     return accountService.getAll();
   }



   @GetMapping("/balance/{accountNumber}")
   public  ResponseEntity<Double> getBalance(@PathVariable String accountNumber)
   {
       double balance = accountService.getBalance(accountNumber);
       return ResponseEntity.ok(balance);
   }

   @PutMapping("/deposit/{accountNumber}/{amount}")

   public ResponseEntity<Account> deposite(@PathVariable String accountNumber , @PathVariable double amount)
   {
       return ResponseEntity.ok(accountService.deposit(accountNumber,amount));
   }

   @PutMapping("/withdraw/{accountNumber}/{amount}")
   public ResponseEntity<Account> withdraw(@PathVariable String accountNumber, @PathVariable double amount)
   {
       return ResponseEntity.ok(accountService.withdraw(accountNumber,amount));
   }


   @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferRequest transferRequest)
   {
       return ResponseEntity.ok(accountService.transferMoney(transferRequest));
   }

   @GetMapping("/transactions/{accountNumber}")
   public ResponseEntity<List<Transaction>> getTransaction(@PathVariable String accountNumber)
   {
       return ResponseEntity.ok(transctionRepository.findByAccountNumber(accountNumber));
   }
   @GetMapping("/statement/{accountNumber}/{year}/{month}")
   public ResponseEntity<List<Transaction>> getMonthlyStatement(@PathVariable String accountNumber,@PathVariable int year,@PathVariable int month)
   {
       return ResponseEntity.ok(accountService.getMonthlyStatement(accountNumber,year,month));
   }
   @GetMapping("/calculateInterest/{accountNumber}/{rate}/{months}")
   public ResponseEntity<Double> calculateInterest(@PathVariable String accountNumber,@PathVariable double rate, @PathVariable int months)
   {
       return ResponseEntity.ok(accountService.calculateInterest(accountNumber,rate,months));
   }




}
