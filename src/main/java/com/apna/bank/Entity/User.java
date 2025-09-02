package com.apna.bank.Entity;

import com.apna.bank.model.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private Role role;
    private boolean active;

    private String accountId;


}
