package com.pellanotes.pella.features.account.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ChangePasswdDto {
    
    @NotBlank(message="No value found for oldPasswd")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message="Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.")
    public String oldPasswd;

    @NotBlank(message="No value found for newpasswd")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message="Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.")
    public String newPasswd;


}
