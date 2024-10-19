package com.example.HomeLoanApp.homeloansecurity.dto;

import com.example.HomeLoanApp.homeloansecurity.modal.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;

}