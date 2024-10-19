package com.example.HomeLoanApp.security.service;


import com.example.HomeLoanApp.homeloansecurity.auth.TokenManager;
import com.example.HomeLoanApp.homeloansecurity.dto.AuthenticationResponse;
import com.example.HomeLoanApp.homeloansecurity.dto.LoginDto;
import com.example.HomeLoanApp.homeloansecurity.dto.RegisterDto;
import com.example.HomeLoanApp.homeloansecurity.modal.Role;
import com.example.HomeLoanApp.homeloansecurity.modal.User;
import com.example.HomeLoanApp.homeloansecurity.repository.UserRepository;
import com.example.HomeLoanApp.homeloansecurity.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private TokenManager tokenManager;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setEmail("john.doe@example.com");
        registerDto.setPassword("password");
        registerDto.setRole("user");
        User user = User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .password("encodedPassword")
                .isEnabled(true)
                .date(LocalDateTime.now())
                .role(Role.USER)
                .build();
        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(tokenManager.generateToken(user)).thenReturn("token");
        AuthenticationResponse response = userDetailsService.registerUser(registerDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testAuthenticate_ValidCredentials() {
        LoginDto loginDto = new LoginDto("username", "password");
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(loginDto.getUsername())
                .password("encodedPassword")
                .roles("USER")
                .build();        User user = new User();
        user.setEmail("username@example.com");
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(userRepository.findByEmail("username")).thenReturn(Optional.of(user));
        when(tokenManager.generateToken(user)).thenReturn("dummy_token");
        AuthenticationResponse response = userDetailsService.authenticate(loginDto);
        assertEquals("dummy_token", response.getToken());
    }

    @Test
    public void testAuthenticate_InvalidCredentials() {
        LoginDto loginDto = new LoginDto("invalid_username", "invalid_password");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new UsernameNotFoundException("User not found"));
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.authenticate(loginDto));
    }
}
