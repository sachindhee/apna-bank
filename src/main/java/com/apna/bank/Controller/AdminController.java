package com.apna.bank.Controller;

import com.apna.bank.Entity.Account;
import com.apna.bank.Entity.Transaction;
import com.apna.bank.Entity.User;
import com.apna.bank.repository.AccountRepository;
import com.apna.bank.repository.TransctionRepository;
import com.apna.bank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private  final AccountRepository accountRepository;
    private final TransctionRepository transctionRepository;

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @GetMapping("/account")
    public List<Account> getAllAccount(){
        return accountRepository.findAll();

    }

    @GetMapping("/transaction")
    public List<Transaction> getAllTransaction()
    {
        return transctionRepository.findAll();
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable String id)
    {
        userRepository.deleteById(id);
        return "User deleted with ID: " + id;
    }
    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable String id)
    {
        accountRepository.deleteById(id);
        return "account deleted with ID: " + id;
    }




}
