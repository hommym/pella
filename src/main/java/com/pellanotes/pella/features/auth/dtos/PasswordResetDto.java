package com.pellanotes.pella.features.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PasswordResetDto {
    

    @NotBlank(message="No value found for email")
    @Email(message="Invalid email format")
    public String email;

    @NotBlank(message="No value found for newPassword")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message="Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.")
    public  String newPassword;


    @NotNull(message = "No value found for otpCode")
    public Integer otpCode;


}
