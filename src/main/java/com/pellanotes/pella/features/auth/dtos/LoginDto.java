package com.pellanotes.pella.features.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    

    @NotBlank(message="No value found for email")
    @Email(message="Invalid email format")
    public String email;

    @NotBlank(message="No value found for password")
    public  String password;



}
