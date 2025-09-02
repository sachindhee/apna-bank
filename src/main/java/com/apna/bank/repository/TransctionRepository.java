package com.apna.bank.repository;

import com.apna.bank.Entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransctionRepository extends MongoRepository<Transaction,String> {
    List<Transaction> findByAccountNumber(String accountNumber);

   // List<Transaction> findByFromAccountNumberOrToAccountNumber(String from,String to);

}
