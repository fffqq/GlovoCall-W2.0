package com.glovoCall.demo.UsersPack;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCredentialDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
