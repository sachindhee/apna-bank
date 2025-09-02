package com.apna.bank.ServiceImpl;

import com.apna.bank.Entity.User;
import com.apna.bank.Service.AccountService;
import com.apna.bank.Service.UserService;
import com.apna.bank.dto.UserDto;
import com.apna.bank.model.Role;
import com.apna.bank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService  accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public User registeByrUser(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registerd");
        }
        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.valueOf(userDto.getRole().toUpperCase()))
                .active(true).build();

        return userRepository.save(user);
    }



}
