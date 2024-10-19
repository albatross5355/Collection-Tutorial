package com.example.HomeLoanApp.homeloansecurity.controller;


import com.example.HomeLoanApp.homeloansecurity.auth.TokenManager;
import com.example.HomeLoanApp.homeloansecurity.dto.AuthenticationResponse;
import com.example.HomeLoanApp.homeloansecurity.dto.RegisterDto;
import com.example.HomeLoanApp.homeloansecurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.example.HomeLoanApp.homeloansecurity.dto.LoginDto;
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class UsersController {


    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    public UsersController( AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userDetailsService.authenticate(loginDto));
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(userDetailsService.registerUser(registerDto));
    }
}