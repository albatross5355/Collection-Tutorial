//package com.example.HomeLoanApp.security.auth;
//
//import com.example.HomeLoanApp.homeloansecurity.auth.TokenManager;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.impl.DefaultClaims;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class TokenManagerTest {
//
//    @Mock
//    private Key key;
//
//    @InjectMocks
//    private TokenManager tokenManager;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void generateTokenWithoutExtraClaims() {
//        // Given
//        UserDetails userDetails = new User("username", "password", null);
//        String expectedToken = "expected_token";
//
//        // Mock
//        when(tokenManager.getSignInKey()).thenReturn(key);
//        when(tokenManager.generateToken(any(Map.class), any(UserDetails.class))).thenCallRealMethod();
//
//        // When
//        String generatedToken = tokenManager.generateToken(userDetails);
//
//        // Then
//        assertNotNull(generatedToken);
//    }
//
//    @Test
//    void generateTokenWithExtraClaims() {
//        // Given
//        UserDetails userDetails = new User("username", "password", null);
//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("key1", "value1");
//        String expectedToken = "expected_token";
//
//        // Mock
//        when(tokenManager.getSignInKey()).thenReturn(key);
//        when(tokenManager.generateToken(any(Map.class), any(UserDetails.class))).thenCallRealMethod();
//
//        // When
//        String generatedToken = tokenManager.generateToken(extraClaims, userDetails);
//
//        // Then
//        assertNotNull(generatedToken);
//    }
//
//    @Test
//    void extractClaim() {
//        // Given
//        String token = "token";
//        Claims claims = new DefaultClaims();
//
//        // Mock
//        when(tokenManager.extractAllClaims(token)).thenReturn(claims);
//
//        // When
//        Object extractedClaim = tokenManager.extractClaim(token, Claims::getSubject);
//
//        // Then
//        assertNull(extractedClaim); // Subject is not set in DefaultClaims
//    }
//
//    // Similarly, you can write tests for other methods such as extractAllClaims, getSignInKey, isTokenValid, isTokenExpired, extractExpiration
//    // Ensure to handle different cases and edge cases in your test scenarios.
//}