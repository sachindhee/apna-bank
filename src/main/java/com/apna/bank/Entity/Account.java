package com.apna.bank.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "account")
public class Account {
    @Id
    private String id;
    private String accountNumber;
    private String accountType;
    private double balance;
    private String userId;
    private LocalDateTime createdAt;

}
