package com.pellanotes.pella.features.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;




public class SignUpDto {
    
    

    @NotBlank(message="No value found for name")
    public String fullName;


    @NotBlank(message="No value found for email")
    @Email(message="Invalid email format")
    public  String email;

    @NotBlank(message="No value found for password")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message="Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.")
    public String password;


    @NotBlank(message="No value found for username")
    @Size(max=15,min=5,message="Username must have max length of 15 and min length of 5")
    public String username;


   


}
