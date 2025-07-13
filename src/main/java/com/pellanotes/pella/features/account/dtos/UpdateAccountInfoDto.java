package com.pellanotes.pella.features.account.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateAccountInfoDto {
    


    @NotBlank(message="No value found for email")
    @Email(message="Invalid email format")
    public String email;


    @NotBlank(message="No value found for username")
    @Size(max=15,min=5,message="Username must have max length of 15 and min length of 5")
    public String username;

    
    public String profile;



}
