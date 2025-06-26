package com.pellanotes.pella.features.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VerifyAccountDto {
    

    @NotNull(message = "No value found for otpCode")
    public Integer otpCode;

    @NotBlank(message="No value found for email")
    @Email(message="Invalid email format")
    public String email;

}
