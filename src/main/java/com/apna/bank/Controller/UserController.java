package com.apna.bank.Controller;

import com.apna.bank.Entity.User;
import com.apna.bank.Service.AuthService;
import com.apna.bank.Service.UserService;
import com.apna.bank.dto.LoginRequest;
import com.apna.bank.dto.UserDto;
import com.apna.bank.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/apna-bank")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome to Apna Bank Dashboard!";
    }

    @GetMapping("/allUser")
    public ResponseEntity<List<User>> getAllUser(){

        List<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
        User createrduser = userService.registeByrUser(userDto);
        return ResponseEntity.ok(createrduser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request)
    {
      try
      {
          String tokan = authService.login(request);
         return ResponseEntity.ok(tokan);
      } catch (Exception e) {
          System.out.println(e.getMessage());
          return ResponseEntity.status(401).body(e.getMessage());
      }
    }

}
