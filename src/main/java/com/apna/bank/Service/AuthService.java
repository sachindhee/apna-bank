package com.apna.bank.Service;


import com.apna.bank.Security.JwtUtil;
import com.apna.bank.dto.LoginRequest;
import com.apna.bank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
   private UserRepository userRepository;

    @Autowired
   private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    private final UserService userService;
    private final AccountService accountService;

    public String login(LoginRequest request) {
        // UsernamePasswordAuthenticationToken create
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Agar authentication pass ho jaye to JWT generate karo
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        return jwtUtil.generateToken(userDetails.getUsername());
    }





}
