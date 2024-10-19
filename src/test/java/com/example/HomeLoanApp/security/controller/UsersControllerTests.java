//package com.example.HomeLoanApp.security.controller;
//
//import com.example.HomeLoanApp.homeloanapplication.controller.HomeLoanApplicationController;
//import com.example.HomeLoanApp.homeloansecurity.auth.SecurityConfig;
//import com.example.HomeLoanApp.homeloansecurity.auth.TokenManager;
//import com.example.HomeLoanApp.homeloansecurity.controller.UsersController;
//import com.example.HomeLoanApp.homeloansecurity.dto.AuthenticationResponse;
//import com.example.HomeLoanApp.homeloansecurity.dto.LoginDto;
//import com.example.HomeLoanApp.homeloansecurity.dto.RegisterDto;
//import com.example.HomeLoanApp.homeloansecurity.modal.User;
//import com.example.HomeLoanApp.homeloansecurity.service.UserDetailsServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.test.context.ContextConfiguration;
//import java.util.Collections;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = UsersController.class, includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class))
//
//public class UsersControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AuthenticationManager authenticationManager;
//
//    @MockBean
//    private TokenManager tokenManager;
//
//    @MockBean
//    private UserDetailsServiceImpl userDetailsService;
//
//    @InjectMocks
//    private UsersController usersController;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//
//    @Test
//    void whenLoginWithValidCredentials_thenReturnsJwtToken() throws Exception {
//        LoginDto loginDto = new LoginDto("user@example.com", "password");
//        String jwtToken = "token";
//        AuthenticationResponse expectedResponse = AuthenticationResponse.builder().token(jwtToken).build();
//        UserDetails mockUserDetails = org.springframework.security.core.userdetails.User
//                .withUsername("user@example.com")
//                .password("password")
//                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
//                .build();
//
//        Authentication authentication = org.mockito.Mockito.mock(Authentication.class);
//        given(authenticationManager.authenticate(any())).willReturn(authentication);
//        given(authentication.getPrincipal()).willReturn(mockUserDetails);
//        given(tokenManager.generateToken(any(User.class))).willReturn(jwtToken);
//        mockMvc.perform(post("/api/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginDto)))
//                .andExpect(status().isOk());
//    }
//
////    @Test
////    void register_test() throws Exception {
////        RegisterDto registerDto = new RegisterDto();
////        when(userDetailsService.registerUser(any(RegisterDto.class)))
////                .thenReturn(new AuthenticationResponse("token"));
////        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/register")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(objectMapper.writeValueAsString(registerDto));
////        mockMvc.perform(request)
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists());
////
////    }
////    @Configuration
////    public static class TestSecurityConfig extends WebSecurityConfigurerAdapter {
////        @Override
////        protected void configure(HttpSecurity http) throws Exception {
////            http.authorizeRequests()
////                    .antMatchers("/api/login/**", "/api/register/**").permitAll()
////                    .anyRequest().authenticated()
////                    .and().csrf().disable(); // Disable CSRF for testing
////        }
////    }
//}