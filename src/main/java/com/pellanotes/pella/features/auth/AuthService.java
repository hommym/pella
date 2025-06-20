package com.pellanotes.pella.features.auth;

import org.springframework.stereotype.Service;

import com.pellanotes.pella.common.utils.PasswordService;
import com.pellanotes.pella.database.models.Otp;
import com.pellanotes.pella.database.models.User;
import com.pellanotes.pella.database.repositories.OtpRepo;
import com.pellanotes.pella.database.repositories.UserRepo;
import com.pellanotes.pella.features.auth.dtos.SignUpDto;
import com.pellanotes.pella.features.auth.dtos.SignUpResponse;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
 


    private final UserRepo userRepo;
    private final OtpRepo otpRepo;
    private final PasswordService passwdService;


    public AuthService(UserRepo userRepo,OtpRepo otpRepo,PasswordService passwdService){
        this.userRepo=userRepo;
        this.otpRepo=otpRepo;
        this.passwdService=passwdService;
    }

    @Transactional
    SignUpResponse signup(SignUpDto dto){
        User user= new User(dto.fullName,dto.email,passwdService.encryptPasswd(dto.password),dto.username);
        user= userRepo.save(user);
        // set up otp 
        Otp otp= new Otp(user);
        otp= otpRepo.save(otp);

        //send otp to user



        return new SignUpResponse("Account creation successful, verify account to login.");
    }

    

    

}
