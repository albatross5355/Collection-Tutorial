package com.example.HomeLoanApp;

import com.example.HomeLoanApp.homeloansecurity.auth.TokenManager;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public TokenManager tokenManager() {
        return Mockito.mock(TokenManager.class);
    }
}